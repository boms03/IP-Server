package hackaton.ip_backend.service;

import hackaton.ip_backend.dto.request.MemberRequestDto;
import org.springframework.stereotype.Service;

@Service
public interface MemberService {
    void createAccount(MemberRequestDto.SignUpDto signUpDto);

    String signIn(MemberRequestDto.SignInDto signInDto);

    void updateNickName(Long id, String nickname);
}
