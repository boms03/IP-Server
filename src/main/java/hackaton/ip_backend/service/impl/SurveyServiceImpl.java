package hackaton.ip_backend.service.impl;

import hackaton.ip_backend.common.exceptions.BaseException;
import hackaton.ip_backend.common.response.BaseResponseStatus;
import hackaton.ip_backend.domain.Member;
import hackaton.ip_backend.domain.Survey;
import hackaton.ip_backend.domain.enums.Category;
import hackaton.ip_backend.dto.request.SurveyRequestDto;
import hackaton.ip_backend.dto.response.SurveyResponseDto;
import hackaton.ip_backend.repository.MemberRepository;
import hackaton.ip_backend.repository.SurveyRepository;
import hackaton.ip_backend.service.SurveyService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

@RequiredArgsConstructor
@Service
public class SurveyServiceImpl implements SurveyService {

    private final SurveyRepository surveyRepository;
    private final MemberRepository memberRepository;
    public void createSurvey(Long userId, SurveyRequestDto.PostSurveyDto postSurveyDto) {

        Member member = getMember(userId);

        if(member.getIpAmount()<postSurveyDto.getIpAmount()){
            throw new BaseException(BaseResponseStatus.UNEXPECTED_ERROR);
        }

        member.setIpAmount(member.getIpAmount()-postSurveyDto.getIpAmount());
        member.setUsedIpAmount(postSurveyDto.getIpAmount());


        LocalDate endAt = parseEndAt(postSurveyDto.getEndAt());

        Survey survey = buildSurvey(postSurveyDto, member, endAt);

        surveyRepository.save(survey);
    }

    @Override
    public SurveyResponseDto.GetSurveyDto getSurvey(Long userId) {
        Member member = getMember(userId);
        return SurveyResponseDto.GetSurveyDto.builder()
                .nickname(member.getName())
                .ipAmount(member.getIpAmount())
                .build();
    }

    private Member getMember(Long userId) {
        return memberRepository.findById(userId).orElseThrow(
                () -> new BaseException(BaseResponseStatus.UNEXPECTED_ERROR)
        );
    }

    private LocalDate parseEndAt(String endAt) {
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            return LocalDate.parse(endAt, formatter);
        } catch (DateTimeParseException e) {
            throw new BaseException(BaseResponseStatus.UNEXPECTED_ERROR);
        }
    }

    public Survey buildSurvey(SurveyRequestDto.PostSurveyDto postSurveyDto, Member member, LocalDate endAt) {
        return Survey.builder()
                .member(member)
                .title(postSurveyDto.getTitle())
                .content(postSurveyDto.getContent())
                .firstOption(postSurveyDto.getFirstOption())
                .secondOption(postSurveyDto.getSecondOption())
                .endAt(endAt)
                .ipAmount(postSurveyDto.getIpAmount())
                .category(Category.valueOf(postSurveyDto.getCategory()))
                .build();
    }

}
