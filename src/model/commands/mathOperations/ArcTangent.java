package model.commands.mathOperations;

import model.configuration.Arguments;
import model.configuration.Scope;
import model.exceptions.CommandException;

/**
 * aTan Function
 * @author DhruvKPatel
 *
 */
public class ArcTangent extends OneParamOperation {

	@Override
	public double execute(Arguments args, Scope scope) throws CommandException {
		return Math.toDegrees(Math.atan(args.getDouble(0)));
	}

	@Override
	public String getID() {
		return "ArcTangent";
	}

}
