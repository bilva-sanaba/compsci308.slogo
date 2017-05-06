package model.commands.turtleCommands;

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
public class Left extends OneParamCommand {

	@Override
	public double execute(Arguments args, Scope scope) throws CommandException {
		Trajectory trajectory = scope.getTrajectory();
		TurtleState current = trajectory.getLast().getModifiableCopy();
				
		double angleChange = args.getDouble(0);

		current.left(angleChange);
		
		trajectory.addLast(current);
		return angleChange;
	}

	@Override
	public String getID() {
		return "Left";
	}


}
