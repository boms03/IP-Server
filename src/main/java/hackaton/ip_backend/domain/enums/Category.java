package hackaton.ip_backend.domain.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Category {
	NONE("선택안함"),
	ENTER("연애"),
	ECONOMY("경제/주식"),
	SPORT("스포츠"),
	DAILY("일상");

	private final String description;

}
