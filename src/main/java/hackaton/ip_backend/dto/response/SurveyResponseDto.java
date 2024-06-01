package hackaton.ip_backend.dto.response;

import lombok.Builder;
import lombok.Getter;

public class SurveyResponseDto {
    @Getter
    @Builder
    public static class GetSurveyDto {

        String nickname;

        Long ipAmount;

    }
}
