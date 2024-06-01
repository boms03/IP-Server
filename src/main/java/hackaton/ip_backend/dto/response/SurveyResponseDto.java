package hackaton.ip_backend.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;

public class SurveyResponseDto {
    @Getter
    @Builder
    public static class GetSurveyDto {

        @Schema(name = "nickname", description = "닉네임", example = "에온")
        String nickname;

        @Schema(name = "ipAmount", description = "현재 ip 보유량", example = "20")
        Long ipAmount;

    }
}
