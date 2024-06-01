package hackaton.ip_backend.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;

public class SurveyRequestDto {

    @Getter
    @Builder
    public static class PostSurveyDto {
        @Schema(name = "title", description = "투표 제목", example = "오늘 저녁 뭐먹지")
        String title;

        @Schema(name = "content", description = "투표 사연", example = "서벌 개발자를 꿈꾸구...")
        String content;

        @Schema(name = "firstOption", description = "첫번째 항목", example = "오늘 저녁 뭐먹지")
        String firstOption;

        @Schema(name = "secondOption", description = "두번째 항목", example = "오늘 저녁 뭐먹지")
        String secondOption;

        @Schema(name = "endAt", description = "투표 마감일", example = "2024-03-02")
        String endAt;

        @Schema(name = "ipAmount", description = "베팅 잎", example = "20")
        Long ipAmount;

        @Schema(name = "category", description = "카테고리 선택 NONE(없음), ENTER(연예), ECONOMY(경제/주식), SPORT(스포츠), DAILY(일상)", example = "SPORT")
        String category;


    }
}
