package model;

import configuration.Trajectory;

public interface Model {
	
	/**
	 * Returns a trajectory object that encapsulates
	 * the Turtle's future trajectory of states
	 * give a string that represents commands entered
	 * into GUI text box
	 */
	Trajectory getTrajectory(String commands);
}
