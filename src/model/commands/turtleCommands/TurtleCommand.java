package model.commands.turtleCommands;

import model.commands.AbstractCommand;
import model.configuration.Scope;

/*
 * This entire class is included in my masterpiece
 * 
 * It illustrates how inheritance can be used to 
 * limit the access subclasses have of information.
 * 
 * Because this class only requests a trajectory,
 * all sub-commands of this class will not be given
 * information about the World state, instance variables,
 * and defined commands. This illustrates how my 
 * command-access structure can be used to encapsulate
 * information within certain classes.
 */
/**
 * This class will be a super-class to all "TurtleCommands" and "TurtleQueries".
 * 
 * All of these classes only need a turtle trajectory to complete.
 * 
 * @author DhruvKPatel
 *
 */
public abstract class TurtleCommand extends AbstractCommand {
	
	/**
	 * Returns a scope request for all TurtleCommands in
	 * which the trajectory is the only requested value.
	 */
	public Scope getScopeRequest(){
		return new Scope(false, false, true, false);
	}

}
