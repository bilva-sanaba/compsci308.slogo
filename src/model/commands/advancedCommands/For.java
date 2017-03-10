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

public class For extends AbstractCommand {


	@Override
	public double execute(Arguments args, Scope scope) throws CommandException {
		VariableContainer vars = scope.getVariables();
		
		Arguments myArgs = args.getTList(0).evaluateContents(scope);
		TList actions = args.getTList(1);

		Variable var = myArgs.getVariable(0);
		double start = myArgs.getDouble(1);
		double end = myArgs.getDouble(2);
		double increment = myArgs.getDouble(3);
		
		if(increment == 0) throw new CommandException("Loop will never terminate: increment is 0");
		if((end-start)/increment < 0) throw new CommandException("Loop will never terminate");
		
		double returns = 0;
		double counter = start;
		while(counter <= end){ // upper limit inclusive
			vars.set(var, new Constant(counter));
			Arguments returnArgs = actions.evaluateContents(scope);
			returns = returnArgs.getDouble(returnArgs.numArgs() - 1);
			counter += increment;
		}
		return returns;		
	}

	@Override
	public Arguments getDefaultArgs() {
		return new Arguments(new Token[]{new TList(), new TList()});
	}

	@Override
	public Scope getScopeRequest() {
		return new Scope(true, true, true, true);
	}

	@Override
	public String getID() {
		return "For";
	}

}
