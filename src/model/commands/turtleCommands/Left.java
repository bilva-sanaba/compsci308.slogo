package model.commands.turtleCommands;

import model.Token;
import model.configuration.Arguments;
import model.configuration.Scope;
import model.configuration.Trajectory;
import model.configuration.TurtleState;
import model.exceptions.CommandException;
import model.tokens.Constant;
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
