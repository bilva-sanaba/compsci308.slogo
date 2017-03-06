package model.commands.mathOperations;

import model.Arguments;
import model.Scope;
import model.commands.CommandException;

/**
 * Sums two Constants together
 * @author DhruvKPatel
 */
public class Sum extends TwoParamOperation {

	/**
	 * Adds two arguments
	 * @throws CommandException 
	 */
	@Override
	public double execute(Arguments args, Scope scope) throws CommandException {
		return args.getDouble(0) + args.getDouble(1);
	}

	@Override
	public String getID() {
		return "Sum";
	}

}
