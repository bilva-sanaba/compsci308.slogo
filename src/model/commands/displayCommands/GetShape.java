package model.commands.displayCommands;

import model.commands.turtleCommands.TurtleCommand;
import model.configuration.Arguments;
import model.configuration.Scope;
import model.exceptions.CommandException;

public class GetShape extends TurtleCommand {

	@Override
	public double execute(Arguments args, Scope scope) throws CommandException {
		return scope.getTrajectory().getLast().getShape();
	}

	@Override
	public Arguments getDefaultArgs() {
		return new Arguments();
	}

	@Override
	public String getID() {
		return "GetShape";
	}

}
