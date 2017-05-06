package model.commands.advancedCommands;

import model.Token;
import model.commands.AbstractCommand;
import model.configuration.Arguments;
import model.configuration.Scope;
import model.exceptions.CommandException;
import model.tokens.Constant;
import model.tokens.TList;
import model.tokens.Variable;
import model.tokens.VariableContainer;

/**
 * Does commands for each counter
 * @author DhruvKPatel
 *
 */
public class DoTimes extends AbstractCommand {

	@Override
	public Scope getScopeRequest() {
		return new Scope(true, true, true, true);
	}

	@Override
	public double execute(Arguments args, Scope scope) throws CommandException {
		VariableContainer vars = scope.getVariables();
		
		Arguments myArgs = args.getTList(0).evaluateContents(scope);
		TList actions = args.getTList(1);

		Variable var = myArgs.getVariable(0);
		int limit = (int) myArgs.getDouble(1); // rounds down to nearest in
		
		double returns = 0;
		for(int i = 1; i <= limit; i++){ // upper limit inclusive
			vars.set(var, new Constant(i));
			Arguments returnArgs = actions.evaluateContents(scope);
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
