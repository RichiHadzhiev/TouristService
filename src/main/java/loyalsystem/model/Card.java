package loyalsystem.model;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name = "cards")
@NamedQueries({
	@NamedQuery(name = Card.GET_ALL_CARDS, query = "select c from Card c")
})
public class Card {
	
	public static final String GET_ALL_CARDS = "Card.getAllCards";
	
	public static final BigDecimal scale = new BigDecimal(0.1);
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;
	
	@Column(name = "holder_name")
	private String holderName;
	
	@Column(name = "points")
	private BigDecimal points = new BigDecimal(0);
	
	@Column(name = "turn_over")
	private BigDecimal turnOver = new BigDecimal(0);
	
	@Column(name = "discount")
	private BigDecimal discount;
	
	@ManyToOne
	private Tier tier;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getHolderName() {
		return holderName;
	}

	public void setHolderName(String holderName) {
		this.holderName = holderName;
	}

	public BigDecimal getPoints() {
		return points;
	}

	public void setPoints(BigDecimal points) {
		this.points = points;
	}

	public BigDecimal getTurnOver() {
		return turnOver;
	}

	public void setTurnOver(BigDecimal turnOver) {
		this.turnOver = turnOver;
	}

	public BigDecimal getDiscount() {
		return discount;
	}

	public void setDiscount(BigDecimal discount) {
		this.discount = discount;
	}

	public Tier getTier() {
		return tier;
	}

	public void setTier(Tier tier) {
		this.tier = tier;
	}
	
}
