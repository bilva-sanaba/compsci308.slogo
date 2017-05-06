package model.commands.mathOperations;

import model.configuration.Arguments;
import model.configuration.Scope;
import model.exceptions.CommandException;

/**
 * Returns Pi constant
 * @author DhruvKPatel
 *
 */
public class Pi extends MathOperation {

	@Override
	public double execute(Arguments args, Scope scope) throws CommandException {
		return Math.PI;
	}

	/**
	 * Expected Arguments:
	 * (none)
	 */
	@Override
	public Arguments getDefaultArgs() {
		return new Arguments();
	}

	@Override
	public String getID() {
		return "Pi";
	}

}
