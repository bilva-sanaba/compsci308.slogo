package model.commands.multipleTurtleCommands;

import model.Token;
import model.commands.AbstractCommand;
import model.configuration.Arguments;
import model.configuration.Scope;
import model.tokens.TList;

public abstract class AskCommand extends AbstractCommand {
	
	/**
	 * Returns scope of Ask Commands
	 */
	@Override
	public Scope getScopeRequest(){
		// These will all need omniscience
		return new Scope(true, true, true, true);
	}
	
	/**
	 * Expected Arguments:
	 * 1: List
	 * 2: List
	 */
	@Override
	public Arguments getDefaultArgs() {
		// Both will need List args
		Token[] returnArgs = {new TList(), new TList()};
		return new Arguments(returnArgs);
	}
	
	
}
