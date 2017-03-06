package model.commands.mathOperations;

import model.Arguments;
import model.Scope;
import model.commands.CommandException;

/**
 * Returns Power: (arg1)^(arg2)
 * 
 * @author DhruvKPatel
 */
public class Power extends Sum{
	
	@Override
	public double execute(Arguments args, Scope scope) throws CommandException {
		return Math.pow(args.getDouble(0), args.getDouble(1));
	}
	
	@Override
	public String getID(){
		return "Power";
	}

}
