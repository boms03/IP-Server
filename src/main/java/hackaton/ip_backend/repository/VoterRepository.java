package hackaton.ip_backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import hackaton.ip_backend.domain.Survey;
import hackaton.ip_backend.domain.Voter;

@Repository
public interface VoterRepository extends JpaRepository<Voter, Long> {
	public Integer countVoterBySurvey(Survey survey);
}
