package model.commands.advancedCommands;

import model.commands.AbstractCommand;
import model.configuration.Arguments;
import model.configuration.Scope;
import model.exceptions.CommandException;
import model.tokens.Constant;
import model.tokens.Variable;
import model.tokens.VariableContainer;

/**
 * Sets a variable to a constant value
 * 
 * @author DhruvKPatel
 *
 */
public class MakeVariable extends AbstractCommand{
	
	@Override
	public Scope getScopeRequest() {
		return new Scope(false, true, false, false);
	}

	@Override
	public double execute(Arguments args, Scope scope) throws CommandException {
		VariableContainer vars = scope.getVariables();
		
		vars.set(args.getVariable(0), args.getConstant(1));
	
		return args.getDouble(1);
	}

	@Override
	public Arguments getDefaultArgs() {
		Arguments args = new Arguments();
		args.add(new Variable(null));
		args.add(new Constant(0));
		return args;
	}

	@Override
	public String getID() {
		return "MakeVariable";
	}

}
