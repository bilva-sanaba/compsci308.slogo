package model.commands.turtleCommands;

import model.commands.displayCommands.TurtleDisplayCommand;
import model.configuration.Arguments;
import model.configuration.Scope;
import model.configuration.Trajectory;
import model.configuration.TurtleState;
import model.exceptions.CommandException;
/**
 * SLogo Addition
 * @author jwei528
 *
 */
public class Stamp extends NoParamCommand {

	@Override
	public double execute(Arguments args, Scope scope) throws CommandException {
		Trajectory trajectory = scope.getTrajectory();
		TurtleState current = trajectory.getLast().getModifiableCopy();

		current.addStamp();
		trajectory.addLast(current);
		//current.setStamp(false);
		return current.getID();
	}
	
	@Override
	public String getID() {
		return "Stamp";
	}

}
