package loyalsystem.model;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name = "pricing")
@IdClass(CompositeKey.class)
@NamedQueries({
	@NamedQuery(name = Pricing.GET_ALL_PRICINGS, query = "select p from Pricing p"),
	@NamedQuery(name = Pricing.UPDATE_PRICING, query = "select p from Pricing p where p.fromCity like :fromCity and p.toCity like :toCity")
})
public class Pricing {
	
	public static final String GET_ALL_PRICINGS = "Pricing.getAllPricings";
	
	public static final String UPDATE_PRICING = "Pricing.updatePricing";
	
	@Id
	@Column(name = "from_city")
	private String fromCity;
	
	@Id
	@Column(name = "to_city")
	private String toCity;
	
	@Column(name = "price")
	private BigDecimal price;

	public String getFromCity() {
		return fromCity;
	}

	public void setFromCity(String fromCity) {
		this.fromCity = fromCity;
	}

	public String getToCity() {
		return toCity;
	}

	public void setToCity(String toCity) {
		this.toCity = toCity;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}
	
}
