package model.commands.displayCommands;

import model.Token;
import model.commands.AbstractCommand;
import model.configuration.Arguments;
import model.configuration.Scope;
import model.exceptions.CommandException;
import model.tokens.Constant;

/**
 * 
 * @author DhruvKPatel
 *
 */
public class SetBackground extends AbstractCommand {

	@Override
	public double execute(Arguments args, Scope scope) throws CommandException {
		int index = (int) args.getDouble(0);
		scope.getWorld().setBackground(index);
		return index;
	}

	@Override
	public Arguments getDefaultArgs() {
		return new Arguments(new Token[] {new Constant(0)});
	}

	@Override
	public Scope getScopeRequest() {
		return new Scope(false, false, true, true);
	}

	@Override
	public String getID() {
		return "SetBackground";
	}

}
