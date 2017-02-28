package model;

import model.commands.CommandException;

/**
 * Token: Command
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
	public Token evaluate(Arguments args) throws CommandException;
	
	/**
	 * Gets number of arguments required by the command
	 */
	public int getNumArgs();
	
	/**
	 * Returns true if Command needs the turtle's trajectory,
	 * false if not.
	 */
	public boolean needsTurtleTrajectory();
	
}

