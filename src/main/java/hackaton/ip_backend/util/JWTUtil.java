package hackaton.ip_backend.util;

import hackaton.ip_backend.common.exceptions.BaseException;
import hackaton.ip_backend.common.response.BaseResponseStatus;
import hackaton.ip_backend.domain.enums.Role;
import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;

@Component
@Slf4j
public class JWTUtil {

    @Value("${spring.jwt.secret}")
    private String SECRET;

    private Key key;

    public JWTUtil(@Value("${spring.jwt.secret}")String secret) {


        byte[] byteSecretKey = Decoders.BASE64.decode(secret);
        key = Keys.hmacShaKeyFor(byteSecretKey);
    }

    public String createToken(Long id) {
        Date now = new Date();
        return Jwts.builder()
                .setHeaderParam("type","jwt")
                .claim("userIdx",id)
                .claim("role", Role.ROLE_USER)
                .setIssuedAt(now)
                .setExpiration(new Date(System.currentTimeMillis()+1*(1000*60*60*24*365)))
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();
    }

    /*
    Header에서 Authorization 으로 JWT 추출
    @return String
     */
    public String getJwt(HttpServletRequest request){
        return request.getHeader("Authorization").split(" ")[1];
    }

    /*
    JWT에서 userId 추출
    @return Long
    @throws BaseException
     */
    public Long getUserId(HttpServletRequest request) throws BaseException {
        //1. JWT 추출
        String accessToken = getJwt(request);
        if(accessToken == null || accessToken.isEmpty()){
            throw new BaseException(BaseResponseStatus.EMPTY_JWT);
        }

        // 2. JWT parsing
        Jws<Claims> claims;
        try{
            claims = Jwts.parser()
                    .setSigningKey(SECRET)
                    .parseClaimsJws(accessToken);
        } catch (Exception ignored) {
            throw new BaseException(BaseResponseStatus.INVALID_JWT);
        }

        // 3. userIdx 추출
        Object userIdxObj = claims.getBody().get("userIdx");
        if (userIdxObj == null) {
            throw new BaseException(BaseResponseStatus.INVALID_JWT);
        }

        if (userIdxObj instanceof String) {
            try {
                return Long.parseLong((String) userIdxObj);
            } catch (NumberFormatException e) {
                throw new BaseException(BaseResponseStatus.INVALID_JWT);
            }
        } else if (userIdxObj instanceof Number) {
            return ((Number) userIdxObj).longValue();
        } else {
            throw new BaseException(BaseResponseStatus.INVALID_JWT);
        }
    }

    public String getRole(HttpServletRequest request) throws BaseException{
        //1. JWT 추출
        String accessToken = getJwt(request);
        if(accessToken == null || accessToken.isEmpty()){
            throw new BaseException(BaseResponseStatus.EMPTY_JWT);
        }

        // 2. JWT parsing
        Jws<Claims> claims;
        try{
            claims = Jwts.parser()
                    .setSigningKey(SECRET)
                    .parseClaimsJws(accessToken);
        } catch (Exception ignored) {
            throw new BaseException(BaseResponseStatus.INVALID_JWT);
        }

        // 3. role 추출
        return claims.getBody().get("role", String.class);
    }

    public boolean validateToken(HttpServletRequest request) {
        String accessToken = getJwt(request);
        if (accessToken == null || accessToken.isEmpty()) {
            throw new BaseException(BaseResponseStatus.EMPTY_JWT);
        }
        try {
            Jwts.parser().setSigningKey(SECRET).parseClaimsJws(accessToken);
        } catch (SecurityException | MalformedJwtException e) {
            log.error("Invalid JWT signature");
            return false;
        } catch (UnsupportedJwtException e) {
            log.error("Unsupported JWT token");
            return false;
        } catch (IllegalArgumentException e) {
            log.error("JWT token is invalid");
            return false;
        }
        return true;
    }
}
