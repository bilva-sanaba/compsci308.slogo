package model.commands.mathOperations;

import model.configuration.Arguments;
import model.configuration.Scope;
import model.exceptions.CommandException;

/**
 * Returns 1 if arg1 != arg2, 0 else.
 * @author DhruvKPatel
 */
public class NotEqual extends TwoParamOperation {

	@Override
	public double execute(Arguments args, Scope scope) throws CommandException {
		return args.getDouble(0) != args.getDouble(1) ? 1:0;
	}

	@Override
	public String getID() {
		return "NotEqual";
	}

}
