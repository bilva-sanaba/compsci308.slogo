package model.commands.mathOperations;

import model.Arguments;
import model.Scope;
import model.commands.CommandException;

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
