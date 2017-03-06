package model.commands.mathOperations;

import model.Arguments;
import model.Scope;
import model.commands.CommandException;

/**
 * Cosine Function
 * @author DhruvKPatel
 *
 */
public class Cosine extends OneParamOperation {

	@Override
	public double execute(Arguments args, Scope scope) throws CommandException {
		return Math.cos(Math.toRadians(args.getDouble(0)));
	}

	@Override
	public String getID() {
		return "Cosine";
	}

}
