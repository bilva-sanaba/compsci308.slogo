package model;

import configuration.Trajectory;
import model.commands.CommandException;

public interface Model {
	
	/**
	 * Returns a trajectory object that encapsulates
	 * the Turtle's future trajectory of states
	 * give a string that represents commands entered
	 * into GUI text box
	 * @throws CommandException 
	 */
	public Trajectory getTrajectory(String commands) throws CommandException;
	
	/**
	 * Sets language for model to interpret with
	 */
	public void setLanguage(String language);
}
