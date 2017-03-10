package model.commands.turtleCommands;

import model.Token;
import model.configuration.Arguments;

public abstract class NoParamCommand extends TurtleCommand {
	@Override
	public Arguments getDefaultArgs() {
		return new Arguments();
	}
}
