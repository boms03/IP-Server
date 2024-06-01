package hackaton.ip_backend.repository;

import hackaton.ip_backend.domain.Member;
import hackaton.ip_backend.domain.Survey;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SurveyRepository extends JpaRepository<Survey, Long> {
}
