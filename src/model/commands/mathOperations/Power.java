package model.commands.mathOperations;

import model.Arguments;

/**
 * Returns Power: (arg1)^(arg2)
 * 
 * @author DhruvKPatel
 */
public class Power extends Sum{
	
	@Override
	public double execute(Arguments args) {
		return Math.pow(args.getDouble(0), args.getDouble(1));
	}
	
	@Override
	public String getID(){
		return "Power";
	}

}
