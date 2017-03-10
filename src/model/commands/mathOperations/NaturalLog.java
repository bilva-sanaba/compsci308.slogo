package model.commands.mathOperations;

import model.configuration.Arguments;
import model.configuration.Scope;
import model.exceptions.CommandException;

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
