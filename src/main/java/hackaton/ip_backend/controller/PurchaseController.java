package hackaton.ip_backend.controller;

import hackaton.ip_backend.dto.response.MemberResponseDto;
import hackaton.ip_backend.service.PurchaseService;
import hackaton.ip_backend.util.JWTUtil;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class PurchaseController {

    private final PurchaseService purchaseService;
    private final JWTUtil jwtUtil;

    @PostMapping("mypage/market")
    @Operation(summary="잎 구매(반환은 '남은 잎 & 사용한 잎'으로)")
    public MemberResponseDto.LeafDto purchaseLeaf(HttpServletRequest request, @RequestBody List<Long> productIdList) {
        Long memberId = jwtUtil.getUserId(request);
        return purchaseService.purchaseLeaf(memberId, productIdList);
    }
}
