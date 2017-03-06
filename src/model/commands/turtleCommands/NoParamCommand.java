package model.commands.turtleCommands;

import model.Arguments;
import model.Token;

public abstract class NoParamCommand extends TurtleCommand {
	@Override
	public Arguments getDefaultArgs() {
		return new Arguments();
	}
}
