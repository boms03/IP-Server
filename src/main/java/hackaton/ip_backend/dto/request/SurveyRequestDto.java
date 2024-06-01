package hackaton.ip_backend.dto.request;

import lombok.Builder;
import lombok.Getter;

public class SurveyRequestDto {

    @Getter
    @Builder
    public static class PostSurveyDto {

        String title;

        String content;

        String firstOption;

        String secondOption;

        String endAt;

        Long ipAmount;

        String category;


    }
}
