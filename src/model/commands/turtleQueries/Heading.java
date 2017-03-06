package model.commands.turtleQueries;

import configuration.Trajectory;
import configuration.TurtleState;
import model.Arguments;
import model.Scope;
import model.commands.CommandException;
import model.commands.turtleCommands.TurtleCommand;
/**
 * 
 * @author Jacob Weiss
 *
 */
public class Heading extends TurtleCommand {

	@Override
	public double execute(Arguments args, Scope scope) throws CommandException {
		Trajectory trajectory = scope.getTrajectory();
		TurtleState current = trajectory.getLast().getModifiableCopy();
		return current.getHeading();
	}

	@Override
	public Arguments getDefaultArgs() {
		return new Arguments();
	}

	@Override
	public String getID() {
		// TODO Auto-generated method stub
		return "Heading";
	}

}
