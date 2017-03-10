package model.commands.turtleCommands;

import model.Token;
import model.configuration.Arguments;
import model.configuration.Scope;
import model.configuration.Trajectory;
import model.configuration.TurtleState;
import model.exceptions.CommandException;
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
