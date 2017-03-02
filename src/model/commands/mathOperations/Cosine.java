package model.commands.mathOperations;

import model.Arguments;
import model.Constant;
import model.Token;

/**
 * Cosine Function
 * @author DhruvKPatel
 *
 */
public class Cosine extends OneParamOperation {

	@Override
	public double execute(Arguments args) {
		return Math.cos(Math.toRadians(args.getDouble(0)));
	}

	@Override
	public String getID() {
		return "Cosine";
	}

}
