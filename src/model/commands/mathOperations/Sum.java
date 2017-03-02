package model.commands.mathOperations;

import model.Arguments;
import model.Constant;
import model.Token;

/**
 * Sums two Constants together
 * @author DhruvKPatel
 */
public class Sum extends TwoParamOperation {

	/**
	 * Adds two arguments
	 */
	@Override
	public double execute(Arguments args) {
		return args.getDouble(0) + args.getDouble(1);
	}

	@Override
	public String getID() {
		return "Sum";
	}

}
