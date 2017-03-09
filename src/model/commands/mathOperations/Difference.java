package model.commands.mathOperations;

import model.Arguments;
import model.Scope;
import model.commands.CommandException;

/**
 * Returns difference between two constants
 * 
 * @author DhruvKPatel, Jacob Weiss
 */
public class Difference extends TwoParamOperation{
	
	/**
	 * Adds two arguments
	 * @throws CommandException 
	 */
	@Override
	public double execute(Arguments args, Scope scope) throws CommandException {
		double ans = args.getDouble(0);
		for(int i = 1; i < args.numArgs(); i++){
			ans-=args.getDouble(i);
		}
		return ans;
	}
	
	@Override
	public String getID(){
		return "Difference";
	}

}
