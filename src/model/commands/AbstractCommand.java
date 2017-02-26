package model.commands;

import configuration.Arguments;
import model.TokenType;

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
	 * This method is called when each command must execute.
	 * 
	 * It first checks the given Arguments for correct format,
	 * then calls on subclass to complete execution.
	 * @throws CommandException 
	 */
	public int execute(Arguments args) throws CommandException{
		if(!hasValidArguments(args)) throw new CommandException(String.format("Invalid arguments for Command: %s", getID()));
		else return doLogic(args);
	}
	
	/**
	 * Defines all abstract commands as Command type tokens
	 */
	public TokenType getType(){
		return TokenType.COMMAND;
	}
	
	/**
	 * Subclasses use this method to perform actual command.
	 */
	public abstract int doLogic(Arguments args);
	
	/**
	 * Each subclass must have this to be able to test validity of arguments
	 */
	public abstract Arguments getDefaultArgs();
	
	/**
	 * Returns String ID of command
	 */
	public abstract String getID();
}
