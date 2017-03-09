package model.commands.turtleCommands;

import model.Arguments;
import model.Token;
import model.tokens.Constant;

public abstract class OneParamCommand extends TurtleCommand {
	@Override
	public Arguments getDefaultArgs() {
		Token[] defaults = {new Constant(0)};
		return new Arguments(defaults);
	}
}
