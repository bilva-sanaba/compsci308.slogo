package model;

import configuration.Trajectory;

/**
 * This class conceptually represents a Turtle.
 * 
 * Each turtle contains a Trajectory and integer ID.
 * @author DhruvKPatel
 *
 */
public class Turtle {
	private Trajectory trajectory;
	private boolean isActive;

	/**
	 * Constructs a new turtle given trajectory
	 * @param trajectory
	 * @param id
	 */
	public Turtle(Trajectory trajectory, boolean isActive){
		this.trajectory = trajectory;
		this.isActive = isActive;
	}

	/**
	 * Returns turtle's Trajectory
	 * This trajectory is modifiable.
	 * @return
	 */
	public Trajectory getTrajectory(){
		return trajectory;
	}
	
//	/**
//	 * Sets turtle's trajectory
//	 */
//	public void setTrajectory(Trajectory trajectory){
//		this.trajectory = trajectory;
//	}
	
	/**
	 * Returns whether turtle is active
	 * @return
	 */
	public boolean isActive(){
		return isActive;
	}
	
	public void setActive(boolean isActive){
		this.isActive = isActive;
	}

}
