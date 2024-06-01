package hackaton.ip_backend.config.scheduler;

import hackaton.ip_backend.domain.Survey;
import hackaton.ip_backend.domain.enums.Status;
import hackaton.ip_backend.repository.SurveyRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;

@Component
@RequiredArgsConstructor
@Slf4j
public class SchedulerConfig {

    private final SurveyRepository surveyRepository;

    // 매일 00시 정각
    @Scheduled(cron = "0 0 0 * * *")
    public void checkSurveyTime() {
        List<Survey> activeSurveyList = surveyRepository.findAllByStatusOrderByCreatedAtDesc(Status.ACTIVE);
        activeSurveyList.stream()
                .filter(survey -> survey.getCreatedAt().isBefore(LocalDateTime.now().minusDays(1)))
                .forEach(survey -> {
                    survey.setStatus(Status.INACTIVE);
                    surveyRepository.save(survey);
                });


        log.info("만료된 투표 업데이트");
    }
}
