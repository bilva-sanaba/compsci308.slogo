package model.commands.mathOperations;

import model.Arguments;
import model.Constant;
import model.Token;

/**
 * Tan Function
 * @author DhruvKPatel
 *
 */
public class Tangent extends OneParamOperation {

	@Override
	public double execute(Arguments args) {
		return Math.tan(Math.toRadians(args.getDouble(0)));
	}

	@Override
	public String getID() {
		return "Tangent";
	}

}
