package loyalsystem.services;

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
	private EntityManager em;
	
	@Autowired
	private TierService ts;
	
	public List<Card> getAllCards(){
		return em.createNamedQuery("Card.getAllCards", Card.class).setMaxResults(1000).getResultList();
	}
	
	public Card getCardById(Long id) throws Exception {
		Card card = em.find(Card.class, id);
		if(card == null) {
			throw new Exception("There is no card with id " + id);
		}
		return card;
	}
	
	public void addCard(String holderName) {
		Card card = new Card();
		card.setHolderName(holderName);
		card.setTier(ts.getTierById(new Long(1)));
		card.setDiscount(card.getTier().getDiscount());
		em.persist(card);
	}
	
	public void updateTier(Card card) {
		
		Long newId = card.getTier().getId() + 1;
		card.setTier(ts.getTierById(newId));
		card.setDiscount(card.getTier().getDiscount());
	}
}
