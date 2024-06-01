package hackaton.ip_backend.service;

import java.util.List;

import hackaton.ip_backend.dto.response.IpDto;

public interface IfService {
	IpDto.PagingWrapper getSurveyAll(int pageNumber);

	IpDto.PagingWrapper getSurveyCategory(String category, int pageNumber);

	IpDto.SurveyDetailDto getSurveyDetail(Long surveyId);

	void postVote(Long userId, Long surveyId, int vote);
}
