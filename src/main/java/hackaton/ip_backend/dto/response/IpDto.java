package hackaton.ip_backend.dto.response;

import java.time.LocalDate;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.annotation.Nullable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

public class IpDto {
	@Getter
	@Builder
	@AllArgsConstructor
	@NoArgsConstructor
	public static class SurveyDto {

		@Schema(name = "surveyId", description = "투표 ID", example = "1")
		private Long surveyId;

		@Schema(name = "title", description = "투표 질문", example = "투표 질문")
		private String title;

		@Schema(name = "firstOption", description = "첫번째 질문", example = "질문1")
		private String firstOption;

		@Schema(name = "secondOption", description = "두번째 질문", example = "질문2")
		private String secondOption;

		@Schema(name = "ipAmount", description = "걸려있는 잎 갯수", example = "10")
		private Long ipAmount;

		@Schema(name = "prize", description = "상품", example = "상품 이름")
		private String prize;

		@Schema(name = "isIp", description = "투표에 걸린 것이 잎인지 상품인지 true면 잎 false면 상품", example = "true")
		private boolean isIp;

		@Schema(name = "endAt", description = "종료일 (시간은 무조건 23시59분)", example = "YYYY-MM-DD")
		private LocalDate endAt;

		@Schema(name = "voteCount", description = "투표 수", example = "10")
		private Integer voteCount;

		@Schema(name = "category", description = "NONE(없음), ENTER(연예), ECONOMY(경제/주식), SPORT(스포츠), DAILY(일상)")
		private String category;

		public void countVoters(int count) {
			this.voteCount = count;
		}
		public void bindRewardType(@Nullable Long ipAmount,@Nullable String prize) {
			if(ipAmount != null) {
				this.ipAmount = ipAmount;
				isIp = true;
			} else {
				this.prize = prize;
				isIp = false;
			}
		}
	}

	@Getter
	@Builder
	@AllArgsConstructor
	@NoArgsConstructor
	public static class SurveyDetailDto {
		@Schema(name = "surveyId", description = "투표 ID", example = "1")
		private Long surveyId;

		@Schema(name = "title", description = "투표 질문", example = "투표 질문")
		private String title;

		@Schema(name = "content", description = "투표 내용", example = "투표 내용")
		private String content;

		@Schema(name = "firstOption", description = "첫번째 질문", example = "질문1")
		private String firstOption;

		@Schema(name = "secondOption", description = "두번째 질문", example = "질문2")
		private String secondOption;

		@Schema(name = "ipAmount", description = "걸려있는 잎 갯수", example = "10")
		private Long ipAmount;

		@Schema(name = "prize", description = "상품", example = "상품 이름")
		private String prize;

		@Schema(name = "isIp", description = "투표에 걸린 것이 잎인지 상품인지 true면 잎 false면 상품", example = "true")
		private boolean isIp;

		@Schema(name = "endAt", description = "종료일 (시간은 무조건 23시59분)", example = "YYYY-MM-DD")
		private LocalDate endAt;

		@Schema(name = "voteCount", description = "투표 수", example = "10")
		private Integer voteCount;

		public void bindRewardType(@Nullable Long ipAmount,@Nullable String prize) {
			if(ipAmount != null) {
				this.ipAmount = ipAmount;
				isIp = true;
			} else {
				this.prize = prize;
				isIp = false;
			}
		}

		public void countVoters(int count) {
			this.voteCount = count;
		}
	}
}
