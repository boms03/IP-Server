package hackaton.ip_backend.service;

import hackaton.ip_backend.dto.request.SignDto;
import org.springframework.stereotype.Service;

@Service
public interface MemberService {
    void createAccount(SignDto.SignUpDto signUpDto);

    String signIn(SignDto.SignInDto signInDto);
}
