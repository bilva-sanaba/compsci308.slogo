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
 * 
 * @author Jacob Weiss
 *
 */
public class Repeat extends AbstractCommand {

	@Override
	public Scope getScopeRequest() {
		return new Scope(true, true, true, true);
	}

	@Override
	public double execute(Arguments args, Scope scope) throws CommandException {
		VariableContainer vars = scope.getVariables();
		
		int times = (int) args.getDouble(0);
		TList tList= args.getTList(1);
		double result = 0;
		int repcount=1;
		
		for(int i=times; i>0; i--){
			vars.set(new Variable("repcount"), new Constant(repcount));
			Constant r = (Constant) tList.evaluateContents(scope).getLast();
			result = r.getVal();
			repcount++;
		}
		
		return result;
	}

	@Override
	public Arguments getDefaultArgs() {
		Token[] def = {new Constant(0), new TList()};
		return new Arguments(def);
	}

	@Override
	public String getID() {
		return "Repeat";
	}

}
