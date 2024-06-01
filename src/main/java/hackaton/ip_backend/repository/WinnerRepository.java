package hackaton.ip_backend.repository;

import hackaton.ip_backend.domain.Member;
import hackaton.ip_backend.domain.Winner;
import hackaton.ip_backend.domain.enums.Status;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WinnerRepository extends JpaRepository<Winner, Long> {
    public Winner findByMember(Member member);
    public Winner findByMemberAndStatus(Member member, Status status);
}
