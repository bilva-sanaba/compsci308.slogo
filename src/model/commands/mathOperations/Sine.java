package model.commands.mathOperations;

import model.Arguments;
import model.Scope;
import model.commands.CommandException;

/**
 * Sine Function
 * @author DhruvKPatel
 *
 */
public class Sine extends OneParamOperation {

	@Override
	public double execute(Arguments args, Scope scope) throws CommandException {
		return Math.sin(Math.toRadians(args.getDouble(0)));
	}

	@Override
	public String getID() {
		return "Sine";
	}

}
