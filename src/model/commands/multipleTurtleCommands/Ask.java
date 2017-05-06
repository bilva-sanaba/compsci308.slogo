package model.commands.multipleTurtleCommands;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import model.configuration.Arguments;
import model.configuration.Scope;
import model.exceptions.CommandException;
import model.tokens.TList;

/**
 * Class for the "Ask" command
 * 
 * @author DhruvKPatel
 */
public class Ask extends AskCommand {

	@Override
	public double execute(Arguments args, Scope scope) throws CommandException {
		TList turtles = args.getTList(0);
		TList commands = args.getTList(1);
		
		Arguments turtleIndicies = turtles.evaluateContents(scope); // simplifies list
		Arguments results = askThenRevert(argumentsToIndicies(turtleIndicies), commands, scope);
		return results.getDouble(0);
	}
	
	/**
	 * Asks turtles in indicies to complete commands, with given scope
	 * 
	 * After asking, it reverts to its previous state
	 * @throws CommandException 
	 * 
	 * @returns 
	 * 
	 */
	public Arguments askThenRevert(List<Integer> indicies, TList commands, Scope scope) throws CommandException{
		Collection<Integer> previouslyActiveTurtles = scope.getWorld().getActiveTurtleIndicies();
		scope.getWorld().setActiveTurtles(indicies);
		Arguments returnArgs = commands.evaluateContents(scope);
		scope.getWorld().setActiveTurtles(previouslyActiveTurtles);
		return returnArgs;
	}
	
	/**
	 * Converts and Arguments object to a Set of Integers
	 * 
	 * If an argument is not a Constant, will throw an error.
	 * @param a
	 * @return
	 * @throws CommandException
	 */
	public List<Integer> argumentsToIndicies(Arguments a) throws CommandException{
		List<Integer> indicies = new ArrayList<>();
		for(int i = 0; i < a.numArgs(); i++){
			if(a.getConstant(i) == null) throw new CommandException("Only constants valid in argument 1 of \"AskWith\"");
			indicies.add((int) a.getDouble(i)); // rounds down
		}
		return indicies;		
	}

	@Override
	public String getID() {
		return "Ask";
	}

}
