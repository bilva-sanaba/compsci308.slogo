package model.commands.advancedCommands;

import model.Token;
import model.commands.AbstractCommand;
import model.configuration.Arguments;
import model.configuration.Scope;
import model.configuration.Trajectory;
import model.exceptions.CommandException;
import model.tokens.Constant;
import model.tokens.TList;
/**
 * 
 * @author Jacob Weiss
 *
 */
public class If extends AbstractCommand {

	@Override
	public Scope getScopeRequest() {
		return new Scope(true, true, true, true);
	}

	@Override
	public double execute(Arguments args, Scope scope) throws CommandException {
		int check = (int) args.getDouble(0);
		TList tList= args.getTList(1);
		double result = 0;
		if(check==0){
			return result;
		}
		else{
			Constant r = (Constant)tList.evaluateContents(scope).getLast();
			result = r.getVal();
			return result;
		}
	}

	@Override
	public Arguments getDefaultArgs() {
		Token[] def = {new Constant(0), new TList()};
		return new Arguments(def);
	}

	@Override
	public String getID() {
		// TODO Auto-generated method stub
		return "If";
	}

}
