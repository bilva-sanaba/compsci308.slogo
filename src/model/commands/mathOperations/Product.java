package model.commands.mathOperations;

import model.Arguments;
import model.Scope;
import model.commands.CommandException;

/**
 * Multiplies two Constants together
 * @author DhruvKPatel
 */
public class Product extends TwoParamOperation {

	/**
	 * Multiplies two arguments
	 * @throws CommandException 
	 */
	@Override
	public double execute(Arguments args, Scope scope) throws CommandException {
		return args.getDouble(0) * args.getDouble(1);
	}

	@Override
	public String getID() {
		return "Product";
	}

}
