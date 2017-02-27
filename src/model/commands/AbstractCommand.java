package model.commands;

import configuration.Arguments;
import model.Command;
import model.TokenType;

public abstract class AbstractCommand implements Command{
	
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
	public double execute(Arguments args) throws CommandException{
		try{
			getDefaultArgs().checkForTypeDifferences(args);
		} catch(CommandException e){
			throw new CommandException(String.format("Incorrect arguments for Command: %s (%s)", getID(), e.getMessage()));
		}
		return doLogic(args);
	}
	
	/**
	 * Defines all abstract commands as Command type tokens
	 */
	public TokenType getType(){
		return TokenType.COMMAND;
	}
	
	/*
	 * Below are the abstract methods that must be implemented by all Sub-Commands
	 */
	
	/**
	 * Subclasses use this method to perform actual command.
	 * 
	 * This is basically the "execute" method, but argument
	 * error checking has already been accomplished. You can
	 * cast to your default types without worrying about whether
	 * the argument follows the default type.
	 * @throws CommandException 
	 */
	public abstract double doLogic(Arguments args) throws CommandException;
	
	/**
	 * Each subclass must have this to be able to test validity of arguments.
	 * Follow this comment structure:
	 * 
	 * Expected Arguments:
	 * 1: *TokenType* (*Argument 1*)
	 * 2: *TokenType* (*Argument 2*)
	 * 		 ...
	 */
	public abstract Arguments getDefaultArgs();
	
	/**
	 * Returns String ID of command 
	 * (This will be the same as the ID in the "Commands.properties" resource file).
	 */
	public abstract String getID();
	
	/**
	 * Returns Command's action type
	 * 
	 * (States whether command modifies turtle trajectory)
	 */
	public abstract boolean needsTurtleTrajectory();	
}
