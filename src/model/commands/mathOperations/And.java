package model.commands.mathOperations;

import model.Arguments;
import model.Scope;
import model.commands.CommandException;

/**
 * "And" operator
 * 
 * returns 1 if arg1 and arg2 are non-zero, otherwise 0
 * @author DhruvKPatel
 *
 */
public class And extends OneParamOperation {

	@Override
	public double execute(Arguments args, Scope scope) throws CommandException {
		return (args.getDouble(0) != 0 ) && (args.getDouble(1) != 0)? 1:0;
	}

	@Override
	public String getID() {
		return "And";
	}

}
