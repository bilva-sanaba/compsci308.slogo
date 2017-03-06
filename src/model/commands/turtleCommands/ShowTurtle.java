package model.commands.turtleCommands;

import configuration.Trajectory;
import configuration.TurtleState;
import model.Arguments;
import model.Scope;
import model.Token;
import model.commands.CommandException;
/**
 * 
 * @author Jacob Weiss
 *
 */
public class ShowTurtle extends NoParamCommand {
	@Override
	public double execute(Arguments args, Scope scope) throws CommandException {
		Trajectory trajectory = scope.getTrajectory();
		TurtleState current = trajectory.getLast().getModifiableCopy();

		current.setShowing(true);
		trajectory.addLast(current);
		return 1;
	}

	@Override
	public String getID() {
		// TODO Auto-generated method stub
		return "ShowTurtle";
	}

}
