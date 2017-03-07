package model.commands.mathOperations;

import model.Arguments;
import model.Scope;
import model.commands.CommandException;

/**
 * Returns opposite of argument
 * @author DhruvKPatel
 *
 */
public class Minus extends OneParamOperation {

	@Override
	public double execute(Arguments args, Scope scope) throws CommandException {
		return -args.getDouble(0);
	}

	@Override
	public String getID() {
		return "Minus";
	}

}
