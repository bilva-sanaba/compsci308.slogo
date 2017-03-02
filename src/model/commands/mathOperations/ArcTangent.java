package model.commands.mathOperations;

import model.Arguments;
import model.Constant;
import model.Token;

/**
 * aTan Function
 * @author DhruvKPatel
 *
 */
public class ArcTangent extends OneParamOperation {

	@Override
	public double execute(Arguments args) {
		return Math.toDegrees(Math.atan(args.getDouble(0)));
	}

	@Override
	public String getID() {
		return "ArcTangent";
	}

}
