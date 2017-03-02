package model.commands.mathOperations;

import model.Arguments;
import model.Constant;
import model.Token;

/**
 * Multiplies two Constants together
 * @author DhruvKPatel
 */
public class Product extends TwoParamOperation {

	/**
	 * Multiplies two arguments
	 */
	@Override
	public double execute(Arguments args) {
		return args.getDouble(0) * args.getDouble(1);
	}

	@Override
	public String getID() {
		return "Product";
	}

}
