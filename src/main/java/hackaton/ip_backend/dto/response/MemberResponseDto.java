package hackaton.ip_backend.dto.response;

import lombok.Builder;
import lombok.Getter;

public class MemberResponseDto {



    @Getter
    @Builder
    public static class SignInResponseDto {
        String id;
        String name;
        String email;
        public static SignInResponseDto of(String id,String name,String email){
            return SignInResponseDto.builder()
                    .id(id)
                    .name(name)
                    .email(email)
                    .build();
        }
    }

    @Getter
    @Builder
    public static class LeafDto
    {
        Long ipAmount;

        Long usedIpAmount;
    }

    @Getter
    @Builder
    public static class SignNoticeDto
    {
        String token;

        String title;

        Long ipAmount;
    }
}
