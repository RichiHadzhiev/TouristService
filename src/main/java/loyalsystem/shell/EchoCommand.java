package loyalsystem.shell;

import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;

@ShellComponent
public class EchoCommand {

	@ShellMethod("Prints greeting message to the user with the given name as argument")
	public String echo(String name) {
		
		return String.format("Hello, %s! You are using the loyal system's shell.", name);
	}
}
