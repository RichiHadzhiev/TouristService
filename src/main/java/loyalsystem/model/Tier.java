package loyalsystem.model;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name = "tier")
@NamedQueries({
	
	@NamedQuery(name = Tier.GET_TIER_BY_ID, query = "select t from Tier t where t.id like :id"),
	@NamedQuery(name = Tier.GET_ORDERED_TIERS, query = "select t from Tier t order by t.id desc")
})
public class Tier {
	
	public static final String GET_TIER_BY_ID = "Tier.getTierById";
	
	public static final String GET_ORDERED_TIERS = "Tier.getLastTier";
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;
	
	@Column(name = "tier_name")
	private String tierName;
	
	@Column(name = "discount")
	private BigDecimal discount;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTierName() {
		return tierName;
	}

	public void setTierName(String tierName) {
		this.tierName = tierName;
	}

	public BigDecimal getDiscount() {
		return discount;
	}

	public void setDiscount(BigDecimal discount) {
		this.discount = discount;
	}
	
}
