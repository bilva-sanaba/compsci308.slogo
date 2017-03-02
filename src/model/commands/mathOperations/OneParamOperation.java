package model.commands.mathOperations;

import model.Arguments;
import model.Constant;
import model.Token;

/**
 * Super-class for math operations with 2 parameters
 * @author DhruvKPatel
 *
 */
public abstract class OneParamOperation extends MathOperation {
	/**
	 * Expected Arguments:
	 * 1: Constant
	 * 2: Constant
	 */
	@Override
	public Arguments getDefaultArgs() {
		Token[] defaults = {new Constant(0)};
		return new Arguments(defaults);
	}
}
