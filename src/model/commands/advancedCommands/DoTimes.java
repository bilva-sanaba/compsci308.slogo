package model.commands.advancedCommands;

import model.Arguments;
import model.Constant;
import model.Scope;
import model.TList;
import model.Token;
import model.Variable;
import model.VariableContainer;
import model.commands.AbstractCommand;
import model.commands.CommandException;

/**
 * Does commands for each counter
 * @author DhruvKPatel
 *
 */
public class DoTimes extends AbstractCommand {

	@Override
	public Scope getScopeRequest() {
		return new Scope(false, true, false);
	}

	@Override
	public double execute(Arguments args, Scope scope) throws CommandException {
		VariableContainer vars = scope.getVariables();
		
		Arguments myArgs = args.getTList(0).executeChildren(scope);
		TList actions = args.getTList(1);

		Variable var = myArgs.getVariable(0);
		int limit = (int) myArgs.getDouble(1);
		
		double returns = 0;
		for(int i = 1; i <= limit; i++){
			vars.set(var, new Constant(i));
			Arguments returnArgs = actions.executeChildren(scope);
			returns = returnArgs.getDouble(returnArgs.numArgs() - 1);
		}
		return returns;		
	}

	@Override
	public Arguments getDefaultArgs() {
		Token[] t = {new TList(), new TList()};
		return new Arguments(t);
	}

	@Override
	public String getID() {
		return "DoTimes";
	}

}
