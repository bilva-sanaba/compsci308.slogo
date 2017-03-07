package model;

import configuration.Trajectory;
import model.commands.CommandException;
import model.commands.CommandFactory;


public interface Model {
	
	/**
	 * Returns a trajectory object that encapsulates
	 * the Turtle's future trajectory of states
	 * give a string that represents commands entered
	 * into GUI text box
	 * @throws CommandException 
	 * @throws SyntaxException 
	 */
	public Trajectory getTrajectory(String commands) throws CommandException;
	
	/**
	 * Sets language for model to interpret with
	 */
	public void setLanguage(String language);
	
	/**
	 * Returns variable container for GLOBAL variables
	 */
	public VariableContainer getGlobalVariables();

	/**
	 * Returns command factory for GLOBAL COMMANDS
	 * @return
	 */
	public CommandFactory getGlobalCommands();
}
