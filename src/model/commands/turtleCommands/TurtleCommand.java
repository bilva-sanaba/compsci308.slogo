package model.commands.turtleCommands;

import model.Scope;
import model.commands.AbstractCommand;

/**
 * This class will be a super-class to all "TurtleCommands" and "TurtleQueries".
 * 
 * All of these classes only need a turtle trajectory to complete.
 * 
 * @author DhruvKPatel
 *
 */
public abstract class TurtleCommand extends AbstractCommand {

	public Scope getScopeRequest(){
		return new Scope(false, false, true);
	}

}
