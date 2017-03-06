package model.commands.turtleQueries;

import configuration.Trajectory;
import configuration.TurtleState;
import model.Arguments;
<<<<<<< HEAD
import model.Scope;
=======
>>>>>>> 90dca5c3b8cfafb3fb75f3021fb75fac24e47524
import model.commands.CommandException;
import model.commands.turtleCommands.NoParamCommand;
/**
 * 
 * @author Jacob Weiss
 *
 */
public class XCoordinate extends NoParamCommand {

	@Override
	public double execute(Arguments args, Scope scope) throws CommandException {
		Trajectory trajectory = scope.getTrajectory();
		TurtleState current = trajectory.getLast().getModifiableCopy();
		return current.getX();
	}

	@Override
	public String getID() {
		// TODO Auto-generated method stub
		return "XCoordinate";
	}

}