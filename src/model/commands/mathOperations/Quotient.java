package model.commands.mathOperations;

import model.Arguments;
import model.Scope;
import model.commands.CommandException;

/**
 * Divides first constant by second constant
 * @author DhruvKPatel
 */
public class Quotient extends TwoParamOperation {

	@Override
	public double execute(Arguments args, Scope scope) throws CommandException {
		return args.getDouble(0) / args.getDouble(1);
	}

	@Override
	public String getID() {
		return "Quotient";
	}

}
