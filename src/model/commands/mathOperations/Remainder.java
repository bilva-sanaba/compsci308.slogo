package model.commands.mathOperations;

import model.configuration.Arguments;
import model.configuration.Scope;
import model.exceptions.CommandException;

/**
 * Mods first constant by second constant
 * @author DhruvKPatel
 */
public class Remainder extends TwoParamOperation {

	@Override
	public double execute(Arguments args, Scope scope) throws CommandException {
		double ans = args.getDouble(0);
		for(int i = 1; i < args.numArgs(); i++){
			ans = ans % args.getDouble(i);
		}
		return ans;
	}

	@Override
	public String getID() {
		return "Remainder";
	}

}
