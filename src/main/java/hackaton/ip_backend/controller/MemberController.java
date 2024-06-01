package hackaton.ip_backend.controller;

import hackaton.ip_backend.dto.request.SignDto;
import hackaton.ip_backend.service.MemberService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @PostMapping("/signup")
    @Operation(summary="회원가입")
    public String joinProcess(@RequestBody SignDto.SignUpDto signUpDto) {
        memberService.createAccount(signUpDto);
        return "ok";          //나중에 exception 처리 후 수정
    }

}
