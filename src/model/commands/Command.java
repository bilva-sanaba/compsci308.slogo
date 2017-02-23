package model.commands;
/**
 * @author Dhruv K Patel
 * @author Jacob Weiss
 *
 */
public interface Command {
	
	/**
	 * Executes a command with "Message" being a 
	 * data structure for arguments and returns with a variable
	 * type and quantity
	 */
	public int execute();
	
	/**
	 * Returns number of arguments that the Command should have
	 * (Does not include trajectory)
	 */
	public int getNumArgs();
}

