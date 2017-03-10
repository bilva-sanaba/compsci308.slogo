package model.commands.mathOperations;

import model.configuration.Arguments;
import model.configuration.Scope;
import model.exceptions.CommandException;

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
