package model.commands.turtleCommands;

import configuration.Trajectory;
import configuration.TurtleState;
import model.Arguments;
import model.Scope;
import model.Token;
import model.commands.CommandException;
import model.tokens.Constant;
/**
 * 
 * @author Jacob Weiss
 *
 */
public class Forward extends OneParamCommand {

	@Override
	public double execute(Arguments args, Scope scope) throws CommandException {
		Trajectory trajectory = scope.getTrajectory();
		TurtleState current = trajectory.getLast().getModifiableCopy();
				
		double distance = args.getDouble(0);
		
		current.moveForward(distance);
		trajectory.addLast(current);
		return distance;
	}

	@Override
	public String getID() {
		return "Forward";
	}

}
