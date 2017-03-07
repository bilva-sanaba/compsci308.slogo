package model.commands.turtleCommands;

import configuration.Trajectory;
import configuration.TurtleState;
import model.Arguments;
import model.Constant;
import model.Scope;
import model.Token;
import model.commands.CommandException;
/**
 * 
 * @author Jacob Weiss
 *
 */
public class Left extends OneParamCommand {

	@Override
	public double execute(Arguments args, Scope scope) throws CommandException {
		Trajectory trajectory = scope.getTrajectory();
		TurtleState current = trajectory.getLast().getModifiableCopy();
		double heading = current.getHeading();
				
		double angleChange = args.getDouble(0);

		current.setHeading(heading-angleChange);
		trajectory.addLast(current);
		return angleChange;
	}

	@Override
	public String getID() {
		// TODO Auto-generated method stub
		return "Left";
	}


}
