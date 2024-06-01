package hackaton.ip_backend.domain;

import hackaton.ip_backend.domain.enums.Role;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

@Entity(name = "Member")
@Getter
@Builder
@Table(name = "Member")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class Member extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "email", length = 45, nullable = false)
    private String email;

    @Column(name = "nickname", length = 45, nullable = false)
    private String name;

    @Column(name = "password", columnDefinition = "TEXT", nullable = false)
    private String password;

    @Enumerated(EnumType.STRING)
    @Column(name = "role", nullable = false)
    private Role role;

    @Column(name = "ip_amount", nullable = false)
    private Long ipAmount;

    @Column(name = "used_ip_amount", nullable = false)
    private Long usedIpAmount;

    public Member update(String nickname)
    {
        if(nickname != null)
        {
            this.name=nickname;
        }
        return this;
    }
}
