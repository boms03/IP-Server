package hackaton.ip_backend.config.scheduler;

import hackaton.ip_backend.domain.Survey;
import hackaton.ip_backend.domain.Voter;
import hackaton.ip_backend.domain.Winner;
import hackaton.ip_backend.domain.enums.Status;
import hackaton.ip_backend.repository.SurveyRepository;
import hackaton.ip_backend.repository.VoterRepository;
import hackaton.ip_backend.repository.WinnerRepository;
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
    private final VoterRepository voterRepository;
    private final WinnerRepository winnerRepository;

    // 매일 00시 정각
    @Scheduled(cron = "0 0 0 * * *")
    public void checkSurveyTime() {
        List<Survey> activeSurveyList = surveyRepository.findAllByStatusOrderByCreatedAtDesc(Status.ACTIVE);
        activeSurveyList.stream()
                .filter(survey -> survey.getCreatedAt().isBefore(LocalDateTime.now().minusDays(1)))
                .forEach(survey -> {

                    survey.setStatus(Status.INACTIVE);

                    Integer countFirst = voterRepository.countVoterBySurveyAndVote(survey,1);
                    Integer countSecond = voterRepository.countVoterBySurveyAndVote(survey,2);
                    Float countAll = voterRepository.countVoterBySurvey(survey).floatValue();

                    survey.setFirst_percentage(countFirst/countAll);
                    survey.setFirst_percentage(countSecond/countAll);

                    int sampleSize = Math.max(1, (int) (countAll * 0.04));
                    List<Voter> voterList = voterRepository.findVotersBySurvey(survey);

                    for(int i=0;i<sampleSize;i++){
                        int index = (int)(Math.random() * (countAll-1));
                        Winner winner = Winner.builder()
                                .member(voterList.get(index).getMember())
                                .survey(survey)
                                .build();
                        winnerRepository.save(winner);
                        log.info("당첨자 업데이트");
                    }

                });

        log.info("만료된 투표 업데이트");
    }
}
