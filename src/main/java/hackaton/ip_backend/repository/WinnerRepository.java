package hackaton.ip_backend.repository;

import hackaton.ip_backend.domain.Voter;
import hackaton.ip_backend.domain.Winner;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WinnerRepository extends JpaRepository<Winner, Long> {
}
