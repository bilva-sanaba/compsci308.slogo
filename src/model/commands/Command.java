package model.commands;

import configuration.Arguments;
import model.Token;

/**
 * @author Dhruv K Patel
 * @author Jacob Weiss
 *
 */
public interface Command extends Token {
	
	/**
	 * Executes a command with "Message" being a 
	 * data structure for arguments and returns with a variable
	 * type and quantity
	 * @throws CommandException 
	 */
	public int execute(Arguments args) throws CommandException;
	
	/**
	 * Returns number of arguments that the Command should have
	 * (Does not include trajectory)
	 */
	public int getNumArgs();
}

