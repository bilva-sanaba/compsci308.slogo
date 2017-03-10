package model.commands.displayCommands;

import model.Token;
import model.commands.turtleCommands.TurtleCommand;
import model.configuration.Arguments;
import model.configuration.Scope;
import model.configuration.Trajectory;
import model.configuration.TurtleState;
import model.exceptions.CommandException;
import model.tokens.Constant;

/**
 * For all singular changes to turtle display
 * 
 * @author DhruvKPatel
 *
 */
public abstract class TurtleDisplayCommand extends TurtleCommand {

	@Override
	public double execute(Arguments args, Scope scope) throws CommandException {
		Trajectory trajectory = scope.getTrajectory();
		TurtleState current = trajectory.getLast().getModifiableCopy();

		double index = args.getDouble(0);
		
		makeQuickChange((int)(Math.round(index)), current);// round to int

		trajectory.addLast(current);
		return index;
	}

	@Override
	public Arguments getDefaultArgs() {
		return new Arguments(new Token[] {new Constant(0)});
	}

	public abstract void makeQuickChange(int index, TurtleState turtle);

}
