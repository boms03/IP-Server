package hackaton.ip_backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import hackaton.ip_backend.domain.Survey;
import hackaton.ip_backend.domain.Voter;

import java.util.List;

@Repository
public interface VoterRepository extends JpaRepository<Voter, Long> {
	public Integer countVoterBySurvey(Survey survey);

	public Integer countVoterBySurveyAndVote(Survey survey, Integer vote);

	List<Voter> findVotersBySurvey(Survey survey);
}
