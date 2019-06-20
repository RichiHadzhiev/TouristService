package loyalsystem.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name = "sales", indexes = {@Index(name = "index_date",  columnList="date_of_sale")})
@NamedQueries({
	@NamedQuery(name = Sale.GET_ALL_SALES_BY_DATE, query = "select s from Sale s where substring(s.dateOfSale, 1, 10) like :dateOfSale")
})
public class Sale {
	
	public static final String GET_ALL_SALES_BY_DATE = "Sale.getAllSalesByDate";
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;
	
	@Column(name = "price")
	private BigDecimal price;
	
	@Column(name = "discounted_price")
	private BigDecimal discountedPrice;
	
	@Column(name = "points")
	private BigDecimal points;
	
	@Column(name = "date_of_sale")
	private LocalDateTime dateOfSale = LocalDateTime.now();
	
	@ManyToOne
	private Card card;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public BigDecimal getDiscountedPrice() {
		return discountedPrice;
	}

	public void setDiscountedPrice(BigDecimal discountedPrice) {
		this.discountedPrice = discountedPrice;
	}

	public BigDecimal getPoints() {
		return points;
	}

	public void setPoints(BigDecimal points) {
		this.points = points;
	}

	public Card getCard() {
		return card;
	}

	public void setCard(Card card) {
		this.card = card;
	}

	public LocalDateTime getDateOfSale() {
		return dateOfSale;
	}

	public void setDateOfSale(LocalDateTime dateOfSale) {
		this.dateOfSale = dateOfSale;
	}
	
}
