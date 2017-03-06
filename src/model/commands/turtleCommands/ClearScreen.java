package model.commands.turtleCommands;

import configuration.Trajectory;
import configuration.TurtleState;
import model.Arguments;
import model.Scope;
import model.commands.CommandException;
/**
 * 
 * @author Jacob Weiss
 *
 */

public class ClearScreen extends NoParamCommand{

	public double execute(Arguments args, Scope scope) throws CommandException {
		Trajectory trajectory = scope.getTrajectory();
		TurtleState current = trajectory.getLast().getModifiableCopy();
		double xLoc = current.getX();
		double yLoc = current.getY();
		
		double newX = 0;
		double newY = 0;
				
		double distanceTraveled = Math.sqrt(Math.pow(Math.abs(newX - xLoc), 2) + Math.pow(Math.abs(newY - yLoc), 2));

		current.setX(newX);
		current.setY(newY);
		trajectory.clear();
		trajectory.addLast(current);
		return distanceTraveled;
	}

	public String getID() {
		// TODO Auto-generated method stub
		return "ClearScreen";
	}
}
