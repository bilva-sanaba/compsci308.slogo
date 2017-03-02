package model.commands.mathOperations;

import model.Arguments;
import model.Constant;
import model.Token;

/**
 * Mods first constant by second constant
 * @author DhruvKPatel
 */
public class Remainder extends TwoParamOperation {

	@Override
	public double execute(Arguments args) {
		return args.getDouble(0) % args.getDouble(1);
	}

	@Override
	public String getID() {
		return "Remainder";
	}

}
