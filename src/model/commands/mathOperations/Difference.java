package model.commands.mathOperations;

import model.Arguments;
import model.Scope;
import model.commands.CommandException;

/**
 * Returns difference between two constants
 * 
 * @author DhruvKPatel
 */
public class Difference extends TwoParamOperation{
	
	/**
	 * Adds two arguments
	 * @throws CommandException 
	 */
	@Override
	public double execute(Arguments args, Scope scope) throws CommandException {
		return args.getDouble(0) - args.getDouble(1);
	}
	
	@Override
	public String getID(){
		return "Difference";
	}

}
