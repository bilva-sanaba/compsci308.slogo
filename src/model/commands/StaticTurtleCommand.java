package model.commands;

/**
 * This is a super-class for commands that don't
 * need the Turtle's current trajectory to function.
 * 
 * I called this "StaticTurtleCommand" instead of 
 * "StaticCommand" because "Static" is a keyword.
 * 
 * @author DhruvKPatel
 */
public abstract class StaticTurtleCommand extends AbstractCommand {

	/**
	 * Static, so always returns false
	 */
	public boolean needsTurtleTrajectory(){
		return false;
	}

}
