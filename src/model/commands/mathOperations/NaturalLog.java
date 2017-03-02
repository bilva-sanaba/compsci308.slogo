package model.commands.mathOperations;

import model.Arguments;
import model.Constant;
import model.Token;

/**
 * Natural log Function
 * @author DhruvKPatel
 *
 */
public class NaturalLog extends OneParamOperation {

	@Override
	public double execute(Arguments args) {
		return Math.log(args.getDouble(0));
	}

	@Override
	public String getID() {
		return "NaturalLog";
	}

}
