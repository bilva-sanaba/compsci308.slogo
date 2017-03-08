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
		double ans = args.getDouble(0);
		for(int i = 1; i < args.numArgs(); i++){
			ans = Math.pow(ans, args.getDouble(i));
		}
		return ans;
	}
	
	@Override
	public String getID(){
		return "Power";
	}

}
