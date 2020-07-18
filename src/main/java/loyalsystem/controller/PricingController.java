package loyalsystem.controller;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import loyalsystem.model.Pricing;
import loyalsystem.services.PricingService;

@RestController
public class PricingController {
	
	@Autowired
	private PricingService pricingService;
	
	@RequestMapping("/pricings")
	public ResponseEntity<List<Pricing>> getAllPricings() {
		
		return new ResponseEntity<List<Pricing>>(pricingService.getAllPricings(), HttpStatus.OK);
	}
	
	@RequestMapping(method = RequestMethod.POST, value = "/pricings")
	public void addPricing(@RequestBody Pricing pricing) {
		
		pricingService.addPricing(pricing);
	}
	
	@RequestMapping(method = RequestMethod.PUT, value = "/pricings/{fromCity}_{toCity}/{newPrice}")
	public void updatePricing(@PathVariable String fromCity, 
							  @PathVariable String toCity,
							  @PathVariable BigDecimal newPrice) {
		pricingService.updatePricing(fromCity, toCity, newPrice);
	}

}
