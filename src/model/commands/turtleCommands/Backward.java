package model.commands.turtleCommands;

import model.configuration.Arguments;
import model.configuration.Scope;
import model.configuration.Trajectory;
import model.configuration.TurtleState;
import model.exceptions.CommandException;
/**
 * 
 * @author Jacob Weiss
 *
 */
public class Backward extends OneParamCommand {

	@Override
	public double execute(Arguments args, Scope scope) throws CommandException {
		Trajectory trajectory = scope.getTrajectory();
		TurtleState current = trajectory.getLast().getModifiableCopy();
				
		double distance = (-1)*args.getDouble(0);
		
		current.moveForward(distance);
		trajectory.addLast(current);
		return distance;
	}

	@Override
	public String getID() {
		// TODO Auto-generated method stub
		return "Backward";
	}

}
