package hackaton.ip_backend.service.impl;

import hackaton.ip_backend.domain.Member;
import hackaton.ip_backend.dto.request.SignDto;
import hackaton.ip_backend.repository.MemberRepository;
import hackaton.ip_backend.service.MemberService;
import hackaton.ip_backend.util.SHA256;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class MemberServiceImpl implements MemberService {
    private final MemberRepository memberRepository;

    @Override
    public void createAccount(SignDto.SignUpDto signUpDto) {
        String name = signUpDto.getName();
        String email = signUpDto.getEmail();
        String password = signUpDto.getPassword();

        Member member = Member.builder()
                .name(name)
                .email(email)
                .password(SHA256.encrypt(password))
                .role("ROLE_USER")
                .build();

        memberRepository.save(member);
    }
}
