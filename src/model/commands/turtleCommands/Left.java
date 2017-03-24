package model.commands.turtleCommands;

import model.configuration.Arguments;
import model.configuration.Scope;
import model.configuration.Trajectory;
import model.configuration.TurtleState;
import model.exceptions.CommandException;

/*
 * This entire class is part of my masterpiece.
 * 
 * This class completes the path of a concrete command
 * and shows how I was able reduce command classes to 
 * only the essential code that separates them.
 * 
 * Because error checking, the number of needed arguments,
 * and other common tasks are taken care of in a higher level,
 * it leaves a very clean, readable  class at the bottom-most
 * level.
 */

/**
 * 
 * @author Jacob Weiss
 * @author Dhruv K Patel
 *
 */
public class Left extends OneParamCommand {

	/**
	 * Turns turtle left, by first argument
	 */
	@Override
	public double execute(Arguments args, Scope scope) throws CommandException {
		Trajectory trajectory = scope.getTrajectory();
		TurtleState current = trajectory.getLast().getModifiableCopy();
				
		double angleChange = args.getDouble(0);

		current.left(angleChange);
		
		trajectory.addLast(current);
		return angleChange;
	}

	/**
	 * Returns command's identifier
	 */
	@Override
	public String getID() {
		return "Left";
	}
}
