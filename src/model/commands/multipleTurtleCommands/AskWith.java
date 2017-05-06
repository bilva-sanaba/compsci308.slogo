package model.commands.multipleTurtleCommands;

import java.util.ArrayList;
import java.util.Collection;

import model.configuration.Arguments;
import model.configuration.Scope;
import model.exceptions.CommandException;
import model.tokens.TList;

/**
 * 
 * @author DhruvKPatel
 *
 */
public class AskWith extends AskCommand {

	@Override
	public double execute(Arguments args, Scope scope) throws CommandException {
		Ask ask = new Ask();
		TList condition = args.getTList(0);
		TList commands = args.getTList(1);
		
		Collection<Integer> allTurtles = scope.getWorld().getAllTurtleIndicies();
		ArrayList<Integer> activeTurtles = new ArrayList<>();
		
		for(int id : allTurtles){
			ArrayList<Integer> test = new ArrayList<>();
			test.add(id);
			double result = ask.askThenRevert(test, condition, scope).getDouble(0); // first active value of condition is one tested
			if(result != 0) activeTurtles.add(id);
		}
		
		Arguments results = ask.askThenRevert(activeTurtles, commands, scope);
		return results.getDouble(0);
	}


	@Override
	public String getID() {
		return "AskWith";
	}
}
