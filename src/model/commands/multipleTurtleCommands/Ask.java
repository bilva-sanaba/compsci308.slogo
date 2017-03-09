package model.commands.multipleTurtleCommands;

import java.util.HashSet;
import java.util.Set;

import model.Arguments;
import model.Scope;
import model.TList;
import model.Token;
import model.commands.CommandException;

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
		return askThenRevert(turtleIndicies, commands, scope);
	}
	
	/**
	 * Asks turtles in indicies to complete commands, with given scope
	 * 
	 * After asking, it reverts to its previous state
	 * @throws CommandException 
	 * 
	 */
	public double askThenRevert(Arguments indicies, TList commands, Scope scope) throws CommandException{
		Set<Integer> previouslyActiveTurtles = scope.getWorld().getActiveTurtles().keySet();
		
		scope.getWorld().setActiveTurtles(argumentsToIndicies(indicies));
		Arguments returnArgs = commands.evaluateContents(scope);
		scope.getWorld().setActiveTurtles(previouslyActiveTurtles);
		return returnArgs.getDouble(returnArgs.numArgs() - 1);
	}
	
	/**
	 * Converts and Arguments object to a Set of Integers
	 * 
	 * If an argument is not a Constant, will throw an error.
	 * @param a
	 * @return
	 * @throws CommandException
	 */
	private Set<Integer> argumentsToIndicies(Arguments a) throws CommandException{
		Set<Integer> indicies = new HashSet<>();
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
