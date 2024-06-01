package hackaton.ip_backend.repository;

import java.util.List;

import hackaton.ip_backend.domain.enums.Status;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import hackaton.ip_backend.domain.Survey;
import hackaton.ip_backend.domain.enums.Category;

@Repository
public interface SurveyRepository extends JpaRepository<Survey, Long> {

	List<Survey> findAllByStatusOrderByCreatedAtDesc(Pageable pageable, Status status);

	List<Survey> findAllByCategoryAndStatusOrderByCreatedAtDesc(Category category, Pageable pageable, Status status);

	List<Survey> findAllByStatusOrderByCreatedAtDesc(Status status);

	Integer countAllBy();

}
