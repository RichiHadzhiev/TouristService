package loyalsystem.controller;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import loyalsystem.model.Tier;
import loyalsystem.services.TierService;

@RestController
public class TierController {
	
	@Autowired
	private TierService tierService;

	@RequestMapping(method = RequestMethod.POST, value =  "/tiers")
	public void addTier(@RequestParam String tierName, @RequestParam BigDecimal discount){
		tierService.addTier(tierName, discount);
	}
	
	@RequestMapping("/last_tier")
	public ResponseEntity<Tier> getLastTier(){
		
		return new ResponseEntity<Tier>(tierService.getLastTier(), HttpStatus.OK);
	}
	
}
