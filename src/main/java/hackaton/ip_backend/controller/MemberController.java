package hackaton.ip_backend.controller;

import hackaton.ip_backend.common.response.BaseResponse;
import hackaton.ip_backend.dto.request.MemberRequestDto;
import hackaton.ip_backend.dto.response.MemberResponseDto;
import hackaton.ip_backend.service.MemberService;
import hackaton.ip_backend.service.WinnerService;
import hackaton.ip_backend.util.JWTUtil;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;
    private final WinnerService winnerService;
    private final JWTUtil jwtUtil;

    @PostMapping("/signup")
    @Operation(summary="회원가입")
    public void joinProcess(@RequestBody MemberRequestDto.SignUpDto signUpDto) {
        memberService.createAccount(signUpDto);
    }

    @PostMapping("/signin")
    @Operation(summary="로그인")
    public BaseResponse<MemberResponseDto.SignNoticeDto> signInProcess(@RequestBody MemberRequestDto.SignInDto signInDto) {
        String token = memberService.signIn(signInDto);
        return new BaseResponse<>(winnerService.checkWin(token, signInDto.getEmail()));

    }

    @PatchMapping("/setting/nickname")
    @Operation(summary = "닉네임 수정")
    public BaseResponse<String> updateNickName(HttpServletRequest request, @RequestBody String nickname)
    {
        Long id = jwtUtil.getUserId(request);
        memberService.updateNickName(id, nickname);
        return new BaseResponse<>("닉네임 수정 완료");
    }

    @GetMapping("/mypage/market")
    @Operation(summary="잎 저장소에서 남은 잎 & 사용한 잎 전달")
    public MemberResponseDto.LeafDto showLeaf(HttpServletRequest request) {
        Long id = jwtUtil.getUserId(request);
        return memberService.getLeafInfo(id);
    }
}
