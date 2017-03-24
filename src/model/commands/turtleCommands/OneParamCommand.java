package model.commands.turtleCommands;

import model.Token;
import model.configuration.Arguments;
import model.tokens.Constant;

/*
 * This entire class is part of my masterpiece.
 * 
 * Its' purpose is simply to reduce code repetition
 * betweens turtle commands that take in one constant
 * as a parameter. It follows the hierarchy of stripping
 * away the amount of code the concrete command classes
 * need to contain. Each step down brings it down to the bare
 * bones.
 */

public abstract class OneParamCommand extends TurtleCommand {
	
	/**
	 * Expected Arguments:
	 * 1: Constant
	 */
	@Override
	public Arguments getDefaultArgs() {
		Token[] defaults = {new Constant(0)};
		return new Arguments(defaults);
	}
}
