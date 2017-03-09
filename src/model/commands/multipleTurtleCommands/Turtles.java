package model.commands.multipleTurtleCommands;

import model.Arguments;
import model.Scope;
import model.commands.AbstractCommand;
import model.commands.CommandException;

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
