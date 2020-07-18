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
	private EntityManager entityManager;
	
	public Tier getTierById(Long id) {
		
		return entityManager.createNamedQuery(Tier.GET_TIER_BY_ID, Tier.class).setParameter("id", id).getSingleResult();
	}
	
	public Tier getLastTier() {
		
		List<Tier> tierList = entityManager.createNamedQuery(Tier.GET_ORDERED_TIERS, Tier.class).setMaxResults(1).getResultList();
		return tierList.get(0);
	}
	
	public void addTier(String tierName, BigDecimal discount) {
		
		Tier tier = new Tier();
		tier.setTierName(tierName);
		tier.setDiscount(discount);
		entityManager.persist(tier);
	}
}
