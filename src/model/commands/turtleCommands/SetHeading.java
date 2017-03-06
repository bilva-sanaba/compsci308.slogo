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
public class SetHeading extends TurtleCommand {
	@Override
	public double execute(Arguments args, Scope scope) throws CommandException {
		Trajectory trajectory = scope.getTrajectory();
		TurtleState current = trajectory.getLast().getModifiableCopy();
				
		double heading = args.getDouble(0);

		current.setHeading(heading);
		trajectory.addLast(current);
		return heading;
	}

	@Override
	public Arguments getDefaultArgs() {
		Token[] def = {new Constant(0)};
		return new Arguments(def);
	}

	@Override
	public String getID() {
		// TODO Auto-generated method stub
		return "SetHeading";
	}

}
