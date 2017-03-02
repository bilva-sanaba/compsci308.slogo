package model.commands.mathOperations;

import model.Arguments;
import model.Constant;
import model.Token;

/**
 * "Not" operator
 * 
 * returns 1 if arg is 0 and 0 if arg is non-zero
 * @author DhruvKPatel
 *
 */
public class Not extends MathOperation {

	@Override
	public double execute(Arguments args) {
		if(args.getDouble(0) != 0) return 0;
		else return 1;
	}

	/**
 	 * Expected Arguments:
	 * 1: Constant
	 */
	@Override
	public Arguments getDefaultArgs() {
		Token[] def = {new Constant(0)};
		return new Arguments(def);
	}

	@Override
	public String getID() {
		return "Not";
	}

}
