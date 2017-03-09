package model.commands.mathOperations;

import model.Arguments;
import model.Scope;
import model.commands.CommandException;

/**
 * Sums two Constants together
 * @author DhruvKPatel, Jacob Weiss
 */
public class Sum extends TwoParamOperation {

	/**
	 * Adds two arguments
	 * @throws CommandException 
	 */
	@Override
	public double execute(Arguments args, Scope scope) throws CommandException {
		double ans= 0;
		for(int i = 0; i < args.numArgs(); i++){
			ans+=args.getDouble(i);
		}
		return ans;
	}

	@Override
	public String getID() {
		return "Sum";
	}

}
