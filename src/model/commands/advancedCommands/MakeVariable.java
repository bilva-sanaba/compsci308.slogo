package model.commands.advancedCommands;

import model.Arguments;
import model.Constant;
import model.Scope;
import model.Variable;
import model.VariableContainer;
import model.commands.AbstractCommand;
import model.commands.CommandException;

/**
 * Sets a variable to a constant value
 * 
 * @author DhruvKPatel
 *
 */
public class MakeVariable extends AbstractCommand{

	@Override
	public Scope getScopeRequest() {
		return new Scope(false, true, false);
	}

	@Override
	public double execute(Arguments args) throws CommandException {
		VariableContainer vars = getScope().getVariables();
		
		Variable newAddition = args.getVariable(0);
		newAddition.setValue(args.getConstant(1));
		vars.addVariable(newAddition);
		
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
