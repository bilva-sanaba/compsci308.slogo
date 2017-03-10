package model.commands.turtleCommands;

import model.Token;
import model.configuration.Arguments;
import model.configuration.Scope;
import model.configuration.Trajectory;
import model.configuration.TurtleState;
import model.exceptions.CommandException;
import model.tokens.Constant;

/**
 * Set turtle heading to point towards (x,y),
 * where (0,0) points toward the center of the screen.
 * 
 * Returns the number of degrees the turtle turned.
 * @author DhruvKPatel
 *
 */
public class SetTowards extends TwoParamCommand {

	@Override
	public double execute(Arguments args, Scope scope) throws CommandException {
		Trajectory trajectory = scope.getTrajectory();
		TurtleState current = trajectory.getLast().getModifiableCopy();
		double currentX = trajectory.getLast().getX();
		double currentY = trajectory.getLast().getY();
				
		double towardsX = args.getDouble(0);
		double towardsY = args.getDouble(1);
		
		double dx = towardsX - currentX;
		double dy = towardsY - currentY;

		double newHeading = 90 - Math.toDegrees(Math.atan2(dy, dx));
		current.setHeading(newHeading);	
		trajectory.addLast(current);
		return newHeading;
	}

	@Override
	public String getID() {
		return "SetTowards";
	}
}
