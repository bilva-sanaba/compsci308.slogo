package model.commands.turtleCommands;

import configuration.Trajectory;
import configuration.TurtleState;
import model.Arguments;
import model.Constant;
import model.Token;
import model.commands.CommandException;

/**
 * Set turtle heading to point towards (x,y),
 * where (0,0) points toward the center of the screen.
 * 
 * Returns the number of degrees the turtle turned.
 * @author DhruvKPatel
 *
 */
public class SetTowards extends TurtleCommand {

	@Override
	public double execute(Arguments args) throws CommandException {
		Trajectory trajectory = getScope().getTrajectory();
		TurtleState current = trajectory.getLast().getModifiableCopy();
		double currentX = trajectory.getLast().getX();
		double currentY = trajectory.getLast().getY();
				
		double towardsX = args.getDouble(0);
		double towardsY = args.getDouble(1);
		
		double dx = towardsX - currentX;
		double dy = towardsY - currentY;

		double newHeading = Math.toDegrees(Math.atan2(dy, dx));
		current.setHeading(newHeading);	
		trajectory.addLast(current);
		return newHeading;
	}

	/**
	 * Expected Arguments:
	 * 1: Constant (x coordinate)
	 * 2: Constant (y coordinate)
	 */
	@Override
	public Arguments getDefaultArgs() {
		Token[] def = {new Constant(0), new Constant(0)};
		return new Arguments(def);
	}

	@Override
	public String getID() {
		return "SetTowards";
	}
}
