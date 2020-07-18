package loyalsystem.controller;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import loyalsystem.model.Sale;
import loyalsystem.model.dtos.SaleDTO;
import loyalsystem.services.SaleService;

@RestController
public class SaleController {

	@Autowired
	private SaleService saleService;
	
	@RequestMapping("/sales")
	public ResponseEntity<List<SaleDTO>> getAllSalesByDate(@RequestParam String dateOfSale){
		
		List<SaleDTO> saleDTOs = new ArrayList<SaleDTO>();
		List<Sale> sales = saleService.getAllSalesByDate(dateOfSale);
		for(Sale sale : sales) {
			saleDTOs.add(SaleDTO.convertToDTO(sale));
		}
		return new ResponseEntity<List<SaleDTO>>(saleDTOs, HttpStatus.OK);
	}
	
	@RequestMapping(method = RequestMethod.POST, value =  "/sales")
	public void addSale(@RequestParam(required = false) Long cardId, @RequestParam BigDecimal price) throws Exception {
		saleService.addSale(price, cardId);
	}
}
