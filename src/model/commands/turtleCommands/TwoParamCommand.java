package model.commands.turtleCommands;

import model.Arguments;
import model.Constant;
import model.Token;

public abstract class TwoParamCommand extends TurtleCommand {
	@Override
	public Arguments getDefaultArgs() {
		Token[] defaults = {new Constant(0), new Constant(0)};
		return new Arguments(defaults);
	}
}
