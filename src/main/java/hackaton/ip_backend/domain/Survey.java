package hackaton.ip_backend.domain;

import java.time.LocalDate;

import hackaton.ip_backend.domain.enums.Category;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Table
@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Survey extends BaseEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne
	@JoinColumn(name = "Member_id", referencedColumnName = "id", nullable = false)
	private Member member;

	@Enumerated(EnumType.STRING)
	@Column(name = "category", nullable = false)
	private Category category;

	@Column(name = "title", length = 20, nullable = false)
	private String title;

	@Column(name = "content", length = 300, nullable = false)
	private String content;

	@Column(name = "first_option", length = 20, nullable = false)
	private String firstOption;

	@Column(name = "second_option", length = 20, nullable = false)
	private String secondOption;

	@Column(name = "ip_amount")
	private Long ipAmount;

	@Column(name = "prize", length = 100)
	private String prize;

	@Column(name = "end_at", nullable = false)
	private LocalDate endAt;
}
