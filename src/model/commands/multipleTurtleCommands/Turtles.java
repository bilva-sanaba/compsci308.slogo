package model.commands.multipleTurtleCommands;

import model.commands.AbstractCommand;
import model.configuration.Arguments;
import model.configuration.Scope;
import model.exceptions.CommandException;

/**
 * 
 * @author DhruvKPatel
 *
 */
public class Turtles extends AbstractCommand {

	@Override
	public double execute(Arguments args, Scope scope) throws CommandException {
		return scope.getWorld().getAllTurtleIndicies().size();
	}

	@Override
	public Arguments getDefaultArgs() {
		return new Arguments();
	}

	@Override
	public Scope getScopeRequest() {
		return new Scope(false, false, true, true);
	}

	@Override
	public String getID() {
		return "Turtles";
	}

}
