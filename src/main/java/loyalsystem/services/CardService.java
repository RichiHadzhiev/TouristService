package loyalsystem.services;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import loyalsystem.model.Card;

@Service
@Transactional
public class CardService {
	
	@PersistenceContext
	private EntityManager entityManager;
	
	@Autowired
	private TierService tierService;
	
	public List<Card> getAllCards(){
		return entityManager.createNamedQuery("Card.getAllCards", Card.class).setMaxResults(1000).getResultList();
	}
	
	public Card getCardById(Long id) throws Exception {
		Card card = entityManager.find(Card.class, id);
		if(card == null) {
			throw new Exception("There is no card with id " + id);
		}
		return card;
	}
	
	public void addCard(String holderName) {
		Card card = new Card();
		card.setHolderName(holderName);
		card.setTier(tierService.getTierById(new Long(1)));
		card.setDiscount(card.getTier().getDiscount());
		entityManager.persist(card);
	}
	
	public void updateTier(Card card) {
		
		Long tierIter = new Long(1);
		while(tierIter < tierService.getLastTier().getId()) {
			
			BigDecimal turnOver = card.getTurnOver();
			BigDecimal thousand = new BigDecimal(1000);
			if(turnOver.compareTo(thousand.multiply(new BigDecimal(tierIter))) >= 0 &&
					turnOver.compareTo((thousand.multiply(new BigDecimal(tierIter)))
							.add(thousand)) < 0) {
				
				card.setTier(tierService.getTierById(new Long(tierIter)+1));
			}	
			tierIter++;
		}
		
		card.setDiscount(card.getTier().getDiscount());
	}
}
