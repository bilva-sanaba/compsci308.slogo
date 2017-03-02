package model.commands.mathOperations;

import model.Arguments;
import model.commands.CommandException;

/**
 * Returns 1 if arg1 > arg2, 0 else.
 * @author DhruvKPatel
 */
public class GreaterThan extends TwoParamOperation {

	@Override
	public double execute(Arguments args) throws CommandException {
		return args.getDouble(0) > args.getDouble(1) ? 1:0;
	}

	@Override
	public String getID() {
		return "GreaterThan";
	}

}
