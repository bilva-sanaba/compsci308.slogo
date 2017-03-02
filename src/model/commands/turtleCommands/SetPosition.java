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
public class SetPosition extends TurtleCommand {
	@Override
	public double execute(Arguments args) throws CommandException {
		Trajectory trajectory = getScope().getTrajectory();
		TurtleState current = trajectory.getLast().getModifiableCopy();
		double xLoc = current.getX();
		double yLoc = current.getY();
		
		double newX = args.getDouble(0);
		double newY = args.getDouble(1);
		
		double distanceTraveled = Math.sqrt(Math.pow(Math.abs(newX - xLoc), 2) + Math.pow(Math.abs(newY - yLoc), 2));

		current.setX(newX);
		current.setY(newY);
		trajectory.addLast(current);
		return distanceTraveled; //distance turtle moved
	}

	@Override
	public Arguments getDefaultArgs() {
		Token[] def = {new Constant(0), new Constant(0)};
		return new Arguments(def);
	}

	@Override
	public String getID() {
		// TODO Auto-generated method stub
		return "SetPosition";
	}

}
