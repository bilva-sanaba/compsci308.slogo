package model.commands.mathOperations;

import model.Arguments;
import model.commands.CommandException;

/**
 * Returns Pi constant
 * @author DhruvKPatel
 *
 */
public class Pi extends MathOperation {

	@Override
	public double execute(Arguments args) throws CommandException {
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
