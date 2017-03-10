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
public class SetPosition extends TwoParamCommand {
	@Override
	public double execute(Arguments args, Scope scope) throws CommandException {
		Trajectory trajectory = scope.getTrajectory();
		TurtleState current = trajectory.getLast().getModifiableCopy();
		
		double distanceTraveled = current.setPosition(args.getDouble(0), args.getDouble(1));
		trajectory.addLast(current);
		return distanceTraveled; //distance turtle moved
	}

	@Override
	public String getID() {
		return "SetPosition";
	}

}
