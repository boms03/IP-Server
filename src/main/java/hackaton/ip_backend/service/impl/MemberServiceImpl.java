package hackaton.ip_backend.service.impl;

import hackaton.ip_backend.common.exceptions.BaseException;
import hackaton.ip_backend.common.response.BaseResponseStatus;
import hackaton.ip_backend.domain.Member;
import hackaton.ip_backend.domain.enums.Role;
import hackaton.ip_backend.dto.request.MemberRequestDto;
import hackaton.ip_backend.dto.response.MemberResponseDto;
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
    public void createAccount(MemberRequestDto.SignUpDto signUpDto) {
        String name = signUpDto.getName();
        String email = signUpDto.getEmail();
        String password = signUpDto.getPassword();


        Member member = Member.builder()
                .name(name)
                .email(email)
                .password(SHA256.encrypt(password))
                .ipAmount(10L)
                .usedIpAmount(0L)
                .role(Role.ROLE_USER)
                .build();

        memberRepository.save(member);
    }

    @Override
    public String signIn(MemberRequestDto.SignInDto signInDto) {
        String email = signInDto.getEmail();
        String password = signInDto.getPassword();
        String encryptedPassword = SHA256.encrypt(password);

        Optional<Member> optionalMember = memberRepository.findByEmail(email);
        Member member = optionalMember.orElseThrow(()-> new RuntimeException("Member not found"));

        if(encryptedPassword.equals(member.getPassword()))
        {
            return jwtUtil.createToken(member.getId());
        }
        else {
            return null;
        }
    }

    @Override
    public void updateNickName(Long id, String nickname){
        Optional<Member> optionalMember = memberRepository.findById(id);
        Member member = optionalMember.orElseThrow(()-> new RuntimeException("Member not found"));

        member.update(nickname);
    }

    @Override
    public MemberResponseDto.LeafDto getLeafInfo(Long id) {
        Optional<Member> optionalMember = memberRepository.findById(id);
        Member member = optionalMember.orElseThrow(()-> new RuntimeException("Member not found"));

        MemberResponseDto.LeafDto leafDto = MemberResponseDto.LeafDto.builder()
                .ipAmount(member.getIpAmount())
                .usedIpAmount(member.getUsedIpAmount())
                .build();

        return leafDto;
    }
}
