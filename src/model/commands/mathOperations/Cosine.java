package model.commands.mathOperations;

import model.configuration.Arguments;
import model.configuration.Scope;
import model.exceptions.CommandException;

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
