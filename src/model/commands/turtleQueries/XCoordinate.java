package model.commands.turtleQueries;

import configuration.Trajectory;
import configuration.TurtleState;
import model.Arguments;
import model.Constant;
import model.Token;
import model.commands.CommandException;
import model.commands.turtleCommands.TurtleCommand;

public class XCoordinate extends TurtleCommand {

	@Override
	public double execute(Arguments args) throws CommandException {
		Trajectory trajectory = getScope().getTrajectory();
		TurtleState current = trajectory.getLast().getModifiableCopy();
		return current.getX();
	}

	@Override
	public Arguments getDefaultArgs() {
		return new Arguments();
	}

	@Override
	public String getID() {
		// TODO Auto-generated method stub
		return "XCoordinate";
	}

}
