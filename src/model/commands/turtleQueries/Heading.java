package model.commands.turtleQueries;

import model.commands.turtleCommands.NoParamCommand;
import model.commands.turtleCommands.TurtleCommand;
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
public class Heading extends NoParamCommand {

	@Override
	public double execute(Arguments args, Scope scope) throws CommandException {
		Trajectory trajectory = scope.getTrajectory();
		TurtleState current = trajectory.getLast().getModifiableCopy();
		return current.getHeading();
	}

	@Override
	public String getID() {
		// TODO Auto-generated method stub
		return "Heading";
	}

}
