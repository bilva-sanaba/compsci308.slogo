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
public class ID extends AbstractCommand {

	@Override
	public double execute(Arguments args, Scope scope) throws CommandException {
		return scope.getTrajectory().getLast().getID();
	}

	@Override
	public Arguments getDefaultArgs() {
		return new Arguments();
	}

	@Override
	public Scope getScopeRequest() {
		return new Scope(false, false, true, false);
	}

	@Override
	public String getID() {
		return "ID";
	}

}
