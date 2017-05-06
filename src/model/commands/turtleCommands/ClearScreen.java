package model.commands.turtleCommands;

import model.configuration.Arguments;
import model.configuration.Scope;
import model.exceptions.CommandException;

/**
 * Clears screen and turtle trajectory
 * @author DhruvKPatel
 * @author Jacob Weiss
 */
public class ClearScreen extends NoParamCommand{

	public double execute(Arguments args, Scope scope) throws CommandException {
		scope.getTrajectory().clear();
		scope.getWorld().clear();
		return 0;
	}

	@Override
	public Scope getScopeRequest(){
		return new Scope(false, false, true, true); // overrides because needs to clear world
	}
	
	public String getID() {
		return "ClearScreen";
	}
}