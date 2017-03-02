package model.commands.mathOperations;

import model.Arguments;
import model.Constant;
import model.Token;

/**
 * Super-class for math operations with 1 parameter
 * @author DhruvKPatel
 *
 */
public abstract class TwoParamOperation extends MathOperation {
	/**
	 * Expected Arguments:
	 * 1: Constant
	 */
	@Override
	public Arguments getDefaultArgs() {
		Token[] defaults = {new Constant(0), new Constant(0)};
		return new Arguments(defaults);
	}
}
