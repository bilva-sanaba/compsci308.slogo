package model.commands.mathOperations;

import model.Arguments;
import model.Scope;
import model.commands.CommandException;

/**
 * Natural log Function
 * @author DhruvKPatel
 *
 */
public class NaturalLog extends OneParamOperation {

	@Override
	public double execute(Arguments args, Scope scope) throws CommandException {
		return Math.log(args.getDouble(0));
	}

	@Override
	public String getID() {
		return "NaturalLog";
	}

}
