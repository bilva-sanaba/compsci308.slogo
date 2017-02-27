package model.commands.staticTurtle.mathOperations;

import configuration.Arguments;
import model.Constant;
import model.Token;

/**
 * Sums two Constants together
 * @author DhruvKPatel
 */
public class Sum extends MathOperation {

	/**
	 * Adds two arguments
	 */
	@Override
	public double doLogic(Arguments args) {
		return args.getDouble(0) + args.getDouble(1);
	}

	/**
	 * Expected Arguments:
	 * 1: Constant
	 * 2: Constant
	 */
	@Override
	public Arguments getDefaultArgs() {
		Token[] defaults = {new Constant(0), new Constant(0)};
		return new Arguments(defaults);
	}

	@Override
	public String getID() {
		return "Sum";
	}

}
