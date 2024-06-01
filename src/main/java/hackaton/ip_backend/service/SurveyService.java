package hackaton.ip_backend.service;

import hackaton.ip_backend.dto.request.SurveyRequestDto;
import hackaton.ip_backend.dto.response.SurveyResponseDto;

public interface SurveyService {
    void createSurvey(Long userId, SurveyRequestDto.PostSurveyDto postSurveyDto);

    /*SurveyResponseDto.GetSurveyDto getSurvey(Long userId);*/
}
