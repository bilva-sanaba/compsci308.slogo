package model.commands.mathOperations;

import model.Arguments;
import model.Constant;
import model.Token;

/**
 * Divides first constant by second constant
 * @author DhruvKPatel
 */
public class Quotient extends TwoParamOperation {

	@Override
	public double execute(Arguments args) {
		return args.getDouble(0) / args.getDouble(1);
	}

	@Override
	public String getID() {
		return "Quotient";
	}

}
