package hackaton.ip_backend.service.impl;

import hackaton.ip_backend.domain.Member;
import hackaton.ip_backend.domain.enums.Role;
import hackaton.ip_backend.dto.request.SignDto;
import hackaton.ip_backend.repository.MemberRepository;
import hackaton.ip_backend.service.MemberService;
import hackaton.ip_backend.util.JWTUtil;
import hackaton.ip_backend.util.SHA256;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class MemberServiceImpl implements MemberService {
    private final MemberRepository memberRepository;
    private final JWTUtil jwtUtil;

    @Override
    public void createAccount(SignDto.SignUpDto signUpDto) {
        String name = signUpDto.getName();
        String email = signUpDto.getEmail();
        String password = signUpDto.getPassword();

        Member member = Member.builder()
                .name(name)
                .email(email)
                .password(SHA256.encrypt(password))
                .role(Role.ROLE_USER)
                .build();

        memberRepository.save(member);
    }

    @Override
    public String signIn(SignDto.SignInDto signInDto) {
        String email = signInDto.getEmail();
        String password = signInDto.getPassword();
        String encryptedPassword = SHA256.encrypt(password);

        Optional<Member> optionalMember = memberRepository.findByEmail(email);
        Member member = optionalMember.orElseThrow(()-> new RuntimeException("Member not found"));

        if(encryptedPassword.equals(member.getPassword()))
        {
            return jwtUtil.createToken(email);
        }
        else {
            return null;
        }
    }
}
