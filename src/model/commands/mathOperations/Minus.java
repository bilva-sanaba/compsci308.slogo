package model.commands.mathOperations;

import model.Arguments;
import model.Constant;
import model.Token;

/**
 * Returns opposite of argument
 * @author DhruvKPatel
 *
 */
public class Minus extends OneParamOperation {

	@Override
	public double execute(Arguments args) {
		return -args.getDouble(0);
	}

	@Override
	public String getID() {
		return "Minus";
	}

}
