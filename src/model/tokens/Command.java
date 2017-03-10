package model.tokens;

import model.Token;
import model.configuration.Arguments;
import model.configuration.Scope;
import model.exceptions.CommandException;

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
	public Token evaluate(Arguments args, Scope scope) throws CommandException;
	
	/**
	 * Gets number of arguments required by the command
	 */
	public int getNumArgs();
	
	/**
	 * Gets scope request of command (what information the command will need)
	 */
	public Scope getScopeRequest();
	
	/**
	 * Gets String ID of command name
	 */
	public String getID();
	
	/**
	 * Tests for null command
	 */

	public boolean isNullCommand();
	
	/**
	 * Tests for unlimited args
	 */
	
	public boolean hasUnlimitedArgs();
}

