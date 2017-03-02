package model.commands.mathOperations;

import model.Arguments;

/**
 * Returns difference between two constants
 * 
 * @author DhruvKPatel
 */
public class Difference extends TwoParamOperation{
	
	/**
	 * Adds two arguments
	 */
	@Override
	public double execute(Arguments args) {
		return args.getDouble(0) - args.getDouble(1);
	}
	
	@Override
	public String getID(){
		return "Difference";
	}

}
