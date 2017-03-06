package model.commands.turtleQueries;

import configuration.Trajectory;
import configuration.TurtleState;
import model.Arguments;
import model.commands.CommandException;
import model.commands.turtleCommands.NoParamCommand;
import model.commands.turtleCommands.TurtleCommand;
/**
 * 
 * @author Jacob Weiss
 *
 */
public class Heading extends NoParamCommand {

	@Override
	public double execute(Arguments args) throws CommandException {
		Trajectory trajectory = getScope().getTrajectory();
		TurtleState current = trajectory.getLast().getModifiableCopy();
		return current.getHeading();
	}

	@Override
	public String getID() {
		// TODO Auto-generated method stub
		return "Heading";
	}

}
