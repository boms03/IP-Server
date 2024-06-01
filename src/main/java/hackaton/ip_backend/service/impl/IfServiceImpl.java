package hackaton.ip_backend.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import hackaton.ip_backend.common.exceptions.BaseException;
import hackaton.ip_backend.common.response.BaseResponseStatus;
import hackaton.ip_backend.domain.Survey;
import hackaton.ip_backend.domain.enums.Category;
import hackaton.ip_backend.dto.response.IpDto;
import hackaton.ip_backend.repository.SurveyRepository;
import hackaton.ip_backend.repository.VoterRepository;
import hackaton.ip_backend.service.IfService;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class IfServiceImpl implements IfService {

	private final SurveyRepository surveyRepository;
	private final VoterRepository voterRepository;

	@Override
	public List<IpDto.SurveyDto> getSurveyAll(int pageNumber) {
		Pageable pageable = PageRequest.of(pageNumber, 10);

		List<Survey> entities = surveyRepository.findAllByOrderByCreatedAtDesc(pageable);

		return toSurveyDtoList(entities);
	}

	@Override
	public List<IpDto.SurveyDto> getSurveyCategory(String category, int pageNumber) {
		Pageable pageable = PageRequest.of(pageNumber, 10);

		List<Survey> entities = surveyRepository.findAllByCategoryOrderByCreatedAtDesc(Category.valueOf(category), pageable);

		return toSurveyDtoList(entities);
	}

	@Override
	public IpDto.SurveyDetailDto getSurveyDetail(Long surveyId) {
		Survey entity = surveyRepository.findById(surveyId).orElseThrow(
				() -> new BaseException(BaseResponseStatus.SURVEY_NOT_FOUND)
		);

		IpDto.SurveyDetailDto dto = IpDto.SurveyDetailDto.builder()
				.surveyId(entity.getId())
				.title(entity.getTitle())
				.firstOption(entity.getFirstOption())
				.secondOption(entity.getSecondOption())
				.endAt(entity.getEndAt())
				.build();

		int count = voterRepository.countVoterBySurvey(entity);
		dto.countVoters(count);
		dto.bindRewardType(entity.getIpAmount(), entity.getPrize());

		return dto;
	}

	private List<IpDto.SurveyDto> toSurveyDtoList(List<Survey> entities) {
		return entities.stream().map((entity) -> {
			IpDto.SurveyDto dto =
					IpDto.SurveyDto.builder()
							.surveyId(entity.getId())
							.title(entity.getTitle())
							.firstOption(entity.getFirstOption())
							.secondOption(entity.getSecondOption())
							.endAt(entity.getEndAt())
							.category(entity.getCategory().name())
							.build();

			int count = voterRepository.countVoterBySurvey(entity);
			dto.countVoters(count);
			dto.bindRewardType(entity.getIpAmount(), entity.getPrize());

			return dto;
		}).collect(Collectors.toList());
	}
}
