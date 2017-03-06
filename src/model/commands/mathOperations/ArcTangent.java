package model.commands.mathOperations;

import model.Arguments;
import model.Scope;
import model.commands.CommandException;

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
