package loyalsystem.services;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import loyalsystem.model.Card;
import loyalsystem.model.Sale;

@Service
@Transactional
public class SaleService {
	
	@PersistenceContext
	private EntityManager em;
	
	@Autowired 
	private CardService cs;
	
	@Autowired
	private TierService ts;
	
	public List<Sale> getAllSalesByDate(String dateOfSale){
		return em.createNamedQuery(Sale.GET_ALL_SALES_BY_DATE, Sale.class).setParameter("dateOfSale", dateOfSale).setMaxResults(1000).getResultList();
	}
	
	public void addSale(BigDecimal price, Long cardId) throws Exception {
		Sale sale = new Sale();
		sale.setPrice(price);
		if(cardId != null) {
			Card card = cs.getCardById(cardId); //find the card with the given id
			sale.setDiscountedPrice(sale.getPrice().subtract(sale.getPrice()
										.multiply(card.getDiscount().divide(new BigDecimal(100)))));
			sale.setPoints(sale.getDiscountedPrice().multiply(Card.scale));
			card.setPoints(card.getPoints().add(sale.getPoints()));
			card.setTurnOver(card.getTurnOver().add(sale.getDiscountedPrice()));
			//if the card's turnover is greater than the tierId*1000
			//must not hard code the last tier
			if(card.getTurnOver().compareTo(new BigDecimal(card.getTier().getId()).multiply(new BigDecimal(1000))) == 1
					&& card.getTier().getId() != ts.getLastTier().getId()) {
				
				cs.updateTier(card);
			}
			sale.setCard(card);
		}
		else {
			sale.setDiscountedPrice(price);
			sale.setPoints(new BigDecimal(0));
		}
		em.persist(sale);
	}
}
