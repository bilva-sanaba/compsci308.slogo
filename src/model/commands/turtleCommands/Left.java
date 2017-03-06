package model.commands.turtleCommands;

import configuration.Trajectory;
import configuration.TurtleState;
import model.Arguments;
import model.Constant;
import model.Token;
import model.commands.CommandException;
/**
 * 
 * @author Jacob Weiss
 *
 */
public class Left extends OneParamCommand {

	@Override
	public double execute(Arguments args) throws CommandException {
		Trajectory trajectory = getScope().getTrajectory();
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
