package model.commands.mathOperations;

import model.configuration.Arguments;
import model.configuration.Scope;
import model.exceptions.CommandException;

/**
 * "Not" operator
 * 
 * returns 1 if arg is 0 and 0 if arg is non-zero
 * @author DhruvKPatel
 *
 */
public class Not extends OneParamOperation {

	@Override
	public double execute(Arguments args, Scope scope) throws CommandException {
		return args.getDouble(0) == 0? 1:0;
	}

	@Override
	public String getID() {
		return "Not";
	}

}
