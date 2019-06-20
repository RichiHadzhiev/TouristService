package loyalsystem.model.dtos;

import java.math.BigDecimal;

import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;

import loyalsystem.model.Card;

public class CardDTO {
	
	private String holderName;
	
	private BigDecimal points;
	
	private BigDecimal turnOver;
	
	private BigDecimal discount;
	
	private String tier;

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

	public String getTier() {
		return tier;
	}

	public void setTier(String tier) {
		this.tier = tier;
	}
	
	public static CardDTO convertToDTO(Card card) {
		
		ModelMapper mapper = new ModelMapper();
		mapper.addMappings(new PropertyMap<Card, CardDTO>(){
			@Override
			protected void configure() {
				
				map().setTier(source.getTier().getTierName());
			}
		});
		return mapper.map(card, CardDTO.class);
	}
}
