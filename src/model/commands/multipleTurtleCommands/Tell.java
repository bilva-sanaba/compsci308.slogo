package model.commands.multipleTurtleCommands;

import java.util.List;

import model.commands.AbstractCommand;
import model.configuration.Arguments;
import model.configuration.Scope;
import model.exceptions.CommandException;
import model.tokens.TList;

/**
 * 
 * @author DhruvKPatel
 *
 */
public class Tell extends AbstractCommand {

	@Override
	public double execute(Arguments args, Scope scope) throws CommandException {
		Ask ask = new Ask();
		
		List<Integer> newActives = ask.argumentsToIndicies(args.getTList(0).evaluateContents(scope));
		
		scope.getWorld().setActiveTurtles(newActives);
		
		if(newActives.size() == 0) return 0;
		return newActives.get(newActives.size()-1);
	}

	@Override
	public Arguments getDefaultArgs() {
		Arguments args = new Arguments();
		args.add(new TList());
		return args;
	}

	@Override
	public Scope getScopeRequest() {
		return new Scope(true, true, true, true);
	}

	@Override
	public String getID() {
		return "Tell";
	}

}
