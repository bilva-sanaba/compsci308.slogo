package model.commands.mathOperations;

import model.Arguments;
import model.Constant;
import model.Token;

/**
 * Sine Function
 * @author DhruvKPatel
 *
 */
public class Sine extends OneParamOperation {

	@Override
	public double execute(Arguments args) {
		return Math.sin(Math.toRadians(args.getDouble(0)));
	}

	@Override
	public String getID() {
		return "Sine";
	}

}
