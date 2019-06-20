package loyalsystem.services;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import loyalsystem.model.Tier;

@Service
@Transactional
public class TierService {

	@PersistenceContext
	private EntityManager em;
	
	public Tier getTierById(Long id) {
		
		return em.createNamedQuery(Tier.GET_TIER_BY_ID, Tier.class).setParameter("id", id).getSingleResult();
	}
	
	//check if getSingleResult() is good, if not use setMaxResults(1).getResultList()
	public Tier getLastTier() {
		
		List<Tier> tierList = em.createNamedQuery(Tier.GET_ORDERED_TIERS, Tier.class).setMaxResults(1).getResultList();
		return tierList.get(0);
	}
	
	public void addTier(String tierName, BigDecimal discount) {
		
		Tier tier = new Tier();
		tier.setTierName(tierName);
		tier.setDiscount(discount);
		em.persist(tier);
	}
}
