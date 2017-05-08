package model.commands.displayCommands;

import model.commands.AbstractCommand;
import model.configuration.Arguments;
import model.configuration.Scope;
import model.exceptions.CommandException;

/**
 * Clears stamps
 * 
 * @author DhruvKPatel
 *
 */
public class ClearStamps extends AbstractCommand {

	@Override
	public double execute(Arguments args, Scope scope) throws CommandException {
		return scope.getWorld().clearStamps();
	}

	@Override
	public Arguments getDefaultArgs() {
		return new Arguments();
	}

	@Override
	public Scope getScopeRequest() {
		return new Scope(true, true, true, true);
	}

	@Override
	public String getID() {
		return "ClearStamps";
	}

}
