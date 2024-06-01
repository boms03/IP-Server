package hackaton.ip_backend.service;

import hackaton.ip_backend.dto.request.MemberRequestDto;
import hackaton.ip_backend.dto.response.MemberResponseDto;

public interface MemberService {
    void createAccount(MemberRequestDto.SignUpDto signUpDto);

    String signIn(MemberRequestDto.SignInDto signInDto);

    void updateNickName(Long id, String nickname);

    MemberResponseDto.LeafDto getLeafInfo(Long id);
}
