package loyalsystem.services;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import loyalsystem.model.Pricing;

@Service
@Transactional
public class PricingService {

	@PersistenceContext
	private EntityManager em;
	
	public List<Pricing> getAllPricings() {
		return em.createNamedQuery(Pricing.GET_ALL_PRICINGS, Pricing.class).setMaxResults(1000).getResultList();
	}
	
	public void addPricing(Pricing pricing) {
		em.persist(pricing);
	}
	
	public void updatePricing(String fromCity, String toCity, BigDecimal price) throws Exception {
		Pricing pricing = em.createNamedQuery(Pricing.UPDATE_PRICING, Pricing.class)
		.setParameter("fromCity", fromCity).setParameter("toCity", toCity).getSingleResult();
		pricing.setPrice(price);
	}

}