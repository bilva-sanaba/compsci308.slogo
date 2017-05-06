package model.commands.mathOperations;

import model.configuration.Arguments;
import model.configuration.Scope;
import model.exceptions.CommandException;

/**
 * Tan Function
 * @author DhruvKPatel
 *
 */
public class Tangent extends OneParamOperation {

	@Override
	public double execute(Arguments args, Scope scope) throws CommandException {
		return Math.tan(Math.toRadians(args.getDouble(0)));
	}

	@Override
	public String getID() {
		return "Tangent";
	}

}
