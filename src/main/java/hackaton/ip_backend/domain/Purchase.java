package hackaton.ip_backend.domain;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
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

@Table(name = "Purchase")
@Entity(name = "Purchase")
@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Purchase extends BaseEntity{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "Member_id", referencedColumnName = "id", nullable = false)
	private Member member;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "Product_id", referencedColumnName = "id", nullable = false)
	private Product product;

	@Column(name = "total_ip_amount", nullable = false)
	private Long totalIpAmount;

	@Column(name = "total_price", nullable = false)
	private int totalPrice;
}
