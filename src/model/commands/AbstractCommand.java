package model.commands;

import configuration.Arguments;

public abstract class AbstractCommand implements Command{
	
	/**
	 * Returns whether arguments list is of same size
	 * and has same order of types to the default arguments
	 * 
	 * @param args
	 * @return
	 */
	public boolean hasValidArguments(Arguments input){
		return getDefaultArgs().hasSameFormat(input);
	}
	
	/**
	 * Returns number of arguments command requires
	 */
	public int getNumArgs() {
		return getDefaultArgs().numArgs();
	}
	
	/**
	 * Each subclass must have this to be able to test validity of arguments
	 */
	public abstract Arguments getDefaultArgs();
}
