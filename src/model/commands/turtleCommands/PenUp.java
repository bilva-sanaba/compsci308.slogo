package model.commands.turtleCommands;

import model.Token;
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
public class PenUp extends NoParamCommand {
	@Override
	public double execute(Arguments args, Scope scope) throws CommandException {
		Trajectory trajectory = scope.getTrajectory();
		TurtleState current = trajectory.getLast().getModifiableCopy();
		
		current.setPenDown(false);
		trajectory.addLast(current);
		return 0;
	}

	@Override
	public String getID() {
		// TODO Auto-generated method stub
		return "PenUp";
	}

}
