package loyalsystem.shell;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;

import loyalsystem.model.Pricing;
import loyalsystem.services.PricingService;

@ShellComponent
public class PricingCommand {

	@Autowired
	PricingService ps;
	
	@ShellMethod("Lists the prices of all trips.")
	public void pricing(){
		
		for(Pricing p : ps.getAllPricings()) {
			
			System.out.println(p);
		}
	}
}
