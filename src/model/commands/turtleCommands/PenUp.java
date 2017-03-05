package model.commands.turtleCommands;

import configuration.Trajectory;
import configuration.TurtleState;
import model.Arguments;
import model.Constant;
import model.Token;
import model.commands.CommandException;
/**
 * 
 * @author Jacob Weiss
 *
 */
public class PenUp extends NoParamCommand {
	@Override
	public double execute(Arguments args) throws CommandException {
		Trajectory trajectory = getScope().getTrajectory();
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
