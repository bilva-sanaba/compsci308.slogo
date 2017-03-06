package model.commands.turtleQueries;

import configuration.Trajectory;
import configuration.TurtleState;
import model.Arguments;
import model.Scope;
import model.commands.CommandException;
import model.commands.turtleCommands.NoParamCommand;
import model.commands.turtleCommands.TurtleCommand;
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
		// TODO Auto-generated method stub
		return "IsShowing";
	}

}
