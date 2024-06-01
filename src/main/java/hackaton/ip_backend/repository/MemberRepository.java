package hackaton.ip_backend.repository;

import hackaton.ip_backend.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Long> {
}
