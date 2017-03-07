package model.commands.multipleTurtleCommands;

import java.util.HashSet;
import java.util.Set;

import model.Arguments;
import model.Scope;
import model.TList;
import model.Token;
import model.TokenType;
import model.commands.AbstractCommand;
import model.commands.CommandException;

public class Ask extends AbstractCommand {

	@Override
	public double execute(Arguments args, Scope scope) throws CommandException {
		TList turtles = args.getTList(0);
		
		Arguments turtleIndicies = turtles.executeChildren(scope);
		TList commands = args.getTList(1);
		
		Set<Integer> previouslyActiveTurtles = scope.getWorld().getActiveTurtles().keySet();
		scope.getWorld().setActiveTurtles(argumentsToIndicies(turtleIndicies));
		
		Arguments returnArgs = commands.executeChildren(scope);
		scope.getWorld().setActiveTurtles(previouslyActiveTurtles);
		
		return returnArgs.getDouble(returnArgs.numArgs() - 1);
	}
	
	private Set<Integer> argumentsToIndicies(Arguments a) throws CommandException{
		Set<Integer> indicies = new HashSet<>();
		for(int i = 0; i < a.numArgs(); i++){
			if(a.get(i).getType() != TokenType.CONSTANT) throw new CommandException("Only constants valid in argument 1 of \"AskWith\"");
			indicies.add((int) a.getDouble(i)); // rounds down
		}
		return indicies;		
	}

	/**
	 * Expected Arguments:
	 * 1: List
	 * 2: List
	 */
	@Override
	public Arguments getDefaultArgs() {
		Token[] returnArgs = {new TList(), new TList()};
		return new Arguments(returnArgs);
	}

	
	@Override
	public Scope getScopeRequest() {
		return new Scope(true, true, true, true);
	}

	@Override
	public String getID() {
		return "Ask";
	}

}
