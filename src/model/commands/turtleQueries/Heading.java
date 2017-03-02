package model.commands.turtleQueries;

import configuration.Trajectory;
import configuration.TurtleState;
import model.Arguments;
import model.commands.CommandException;
import model.commands.turtleCommands.TurtleCommand;

public class Heading extends TurtleCommand {

	@Override
	public double execute(Arguments args) throws CommandException {
		Trajectory trajectory = getScope().getTrajectory();
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
