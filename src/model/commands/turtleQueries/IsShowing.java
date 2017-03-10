package model.commands.turtleQueries;

import model.commands.turtleCommands.NoParamCommand;
import model.configuration.Arguments;
import model.configuration.Scope;
import model.configuration.Trajectory;
import model.configuration.TurtleState;
import model.exceptions.CommandException;
/**
 * 
 * @author Jacob Weiss
 *
 */
public class IsShowing extends NoParamCommand {

	@Override
	public double execute(Arguments args, Scope scope) throws CommandException {
		Trajectory trajectory = scope.getTrajectory();
		TurtleState current = trajectory.getLast().getModifiableCopy();
		if(current.isShowing()){
			return 1;
		}
		else{
			return 0;
		}
	}

	@Override
	public String getID() {
		return "IsShowing";
	}

}
