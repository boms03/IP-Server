package hackaton.ip_backend.service;

import hackaton.ip_backend.dto.response.MemberResponseDto;

public interface WinnerService {
    MemberResponseDto.SignNoticeDto checkWin(String token, String email);
}
