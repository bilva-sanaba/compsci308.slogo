package model.commands.mathOperations;

import model.Arguments;
import model.Constant;
import model.Token;

/**
 * "Or" operator
 * 
 * returns 1 if arg1 or arg2 is non-zero, otherwise 0
 * @author DhruvKPatel
 *
 */
public class Or extends OneParamOperation {

	@Override
	public double execute(Arguments args) {
		return (args.getDouble(0) != 0 ) || (args.getDouble(1) != 0)? 1:0;
	}

	@Override
	public String getID() {
		return "Or";
	}

}
