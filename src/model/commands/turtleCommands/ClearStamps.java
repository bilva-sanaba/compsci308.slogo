package model.commands.turtleCommands;

import model.configuration.Arguments;
import model.configuration.Scope;
import model.configuration.Trajectory;
import model.configuration.TurtleState;
import model.exceptions.CommandException;

public class ClearStamps extends NoParamCommand {

	@Override
	public double execute(Arguments args, Scope scope) throws CommandException {
		Trajectory trajectory = scope.getTrajectory();
		TurtleState current = trajectory.getLast().getModifiableCopy();

		int stamps = current.getStampCount();
		current.clearStamps();
		trajectory.addLast(current);
		return stamps;
	}
	
	@Override
	public String getID() {
		return "ClearStamps";
	}
}
