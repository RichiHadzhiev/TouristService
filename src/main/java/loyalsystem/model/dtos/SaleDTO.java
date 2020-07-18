package loyalsystem.model.dtos;

import java.math.BigDecimal;

import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;

import loyalsystem.model.Sale;

public class SaleDTO {

	private BigDecimal price;
	
	private BigDecimal discountedPrice;
	
	private BigDecimal points;
	
	private String dateOfSale;
	
	private String holderName;

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

	public String getDateOfSale() {
		return dateOfSale;
	}

	public void setDateOfSale(String dateOfSale) {
		this.dateOfSale = dateOfSale;
	}

	public String getHolderName() {
		return holderName;
	}

	public void setHolderName(String holderName) {
		this.holderName = holderName;
	}
	
	public static SaleDTO convertToDTO(Sale sale) {
		
		ModelMapper mapper = new ModelMapper();
		mapper.addMappings(new PropertyMap<Sale, SaleDTO>() {
			@Override
			protected void configure() {
				map().setHolderName(source.getCard().getHolderName());
			}
		});
		return mapper.map(sale, SaleDTO.class);
	}
}
