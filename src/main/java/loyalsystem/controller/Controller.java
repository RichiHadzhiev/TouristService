package loyalsystem.controller;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import loyalsystem.model.Card;
import loyalsystem.model.Pricing;
import loyalsystem.model.Sale;
import loyalsystem.model.Tier;
import loyalsystem.model.dtos.CardDTO;
import loyalsystem.model.dtos.SaleDTO;
import loyalsystem.services.CardService;
import loyalsystem.services.PricingService;
import loyalsystem.services.SaleService;
import loyalsystem.services.TierService;

@RestController
public class Controller {
	
	@Autowired
	private PricingService ps;
	
	@Autowired
	private CardService cs;
	
	@Autowired
	private SaleService ss;
	
	@Autowired
	private TierService ts;
	
	@RequestMapping("/pricings")
	public ResponseEntity<List<Pricing>> getAllPricings() {
		
		return new ResponseEntity<List<Pricing>>(ps.getAllPricings(), HttpStatus.OK);
	}
	
	@RequestMapping(method = RequestMethod.POST, value = "/pricings")
	public void addPricing(@RequestBody Pricing pricing) {
		
		ps.addPricing(pricing);
	}
	
	@RequestMapping(method = RequestMethod.PUT, value = "/pricings/{fromCity}_{toCity}/{newPrice}")
	public void updatePricing(@PathVariable String fromCity, @PathVariable String toCity,
							  @PathVariable BigDecimal newPrice) throws Exception {
		ps.updatePricing(fromCity, toCity, newPrice);
	}
	
	@RequestMapping("/cards")
	public ResponseEntity<List<CardDTO>> getAllCards() {
		
		List<CardDTO> dtos = new ArrayList<CardDTO>();
		List<Card> cards = cs.getAllCards();
		for(Card card : cards) {
			dtos.add(CardDTO.convertToDTO(card));
		}
		return new ResponseEntity<List<CardDTO>>(dtos, HttpStatus.OK);
	}
	
	@RequestMapping("/cards/{id}")
	public ResponseEntity<Card> getCardById(@PathVariable Long id) throws Exception {
		
		return new ResponseEntity<Card>(cs.getCardById(id), HttpStatus.OK);
	}
	
	@RequestMapping(method = RequestMethod.POST, value = "/cards")
	public void addCard(@RequestParam String holderName) {
		
		cs.addCard(holderName);
	}
	
	@RequestMapping("/sales")
	public ResponseEntity<List<SaleDTO>> getAllSalesByDate(@RequestParam String dateOfSale){
		
		List<SaleDTO> dtos = new ArrayList<SaleDTO>();
		List<Sale> sales = ss.getAllSalesByDate(dateOfSale);
		for(Sale sale : sales) {
			dtos.add(SaleDTO.convertToDTO(sale));
		}
		return new ResponseEntity<List<SaleDTO>>(dtos, HttpStatus.OK);
	}
	
	@RequestMapping(method = RequestMethod.POST, value =  "/sales")
	public void addSale(@RequestParam(required = false) Long cardId, @RequestParam BigDecimal price) throws Exception {
		ss.addSale(price, cardId);
	}
	
	@RequestMapping(method = RequestMethod.POST, value =  "/tiers")
	public void addTier(@RequestParam String tierName, @RequestParam BigDecimal discount){
		ts.addTier(tierName, discount);
	}
	
	@RequestMapping("/last_tier")
	public ResponseEntity<Tier> getLastTier(){
		
		return new ResponseEntity<Tier>(ts.getLastTier(), HttpStatus.OK);
	}
}
