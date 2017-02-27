package model.commands;

import configuration.Trajectory;

/**
 * This is a super-class for commands that need
 * the Turtle's current trajectory to function.
 * 
 * @author DhruvKPatel
 */
public abstract class DynamicTurtleCommand extends AbstractCommand {
	private Trajectory trajectory;
	
	/**
	 * Dynamic, so always returns true
	 */
	public boolean needsTurtleTrajectory(){
		return true;
	}
	
	/**
	 * Sets command access to the turtle's trajectory
	 * 
	 * For dynamic commands, the trajectory acts as 
	 * a "reciever" in the "command" design pattern.
	 * 
	 * http://www.oodesign.com/command-pattern.html
	 */
	public void setTrajectory(Trajectory t){
		trajectory = t;
	}
	
	/**
	 * Retrieves turtle's trajectory (for use in subclasses)
	 * 
	 * If trajectory has not been set, throws CommandException
	 * @return
	 * @throws CommandException 
	 */
	protected Trajectory getTrajectory() throws CommandException{
		// should only result in error if command class is faulty.
		if(trajectory == null) throw new CommandException("Trajectory not found");
		return trajectory;
	}
}
