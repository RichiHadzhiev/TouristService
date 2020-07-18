package loyalsystem.controller;

import java.util.ArrayList;
import java.util.List;

import loyalsystem.model.dtos.CardDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import loyalsystem.model.Card;
import loyalsystem.services.CardService;

@RestController
public class CardController {

	@Autowired
	private CardService cardService;

	@RequestMapping("/cards")
	public ResponseEntity<List<CardDTO>> getAllCards() {
		
		List<CardDTO> cardDTOs = new ArrayList<CardDTO>();
		List<Card> cards = cardService.getAllCards();
		for(Card card : cards) {
			cardDTOs.add(CardDTO.convertToDTO(card));
		}
		return new ResponseEntity<List<CardDTO>>(cardDTOs, HttpStatus.OK);
	}
	
	@RequestMapping("/cards/{id}")
	public ResponseEntity<Card> getCardById(@PathVariable Long id) throws Exception {
		
		return new ResponseEntity<Card>(cardService.getCardById(id), HttpStatus.OK);
	}
	
	@RequestMapping(method = RequestMethod.POST, value = "/cards")
	public void addCard(@RequestParam String holderName) {
		
		cardService.addCard(holderName);
	}
}
