package model.commands.mathOperations;

import model.configuration.Arguments;
import model.configuration.Scope;
import model.exceptions.CommandException;

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
		double ans = 1;
		for(int i = 0; i < args.numArgs(); i++){
			ans = ans*args.getDouble(i);
		}
		return ans;
	}


	@Override
	public String getID() {
		return "Product";
	}

}
