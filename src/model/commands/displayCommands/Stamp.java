package model.commands.displayCommands;

import model.World;
import model.commands.AbstractCommand;
import model.configuration.Arguments;
import model.configuration.Scope;
import model.exceptions.CommandException;

/**
 * Stamps turtle
 * 
 * @author DhruvKPatel
 *
 */
public class Stamp extends AbstractCommand {

	public Stamp() {
	}

	@Override
	public double execute(Arguments args, Scope scope) throws CommandException {
		World world = scope.getWorld();
		world.stampTurtle(scope.getTrajectory().getLast());
		return scope.getTrajectory().getLast().getID();
	}

	@Override
	public Arguments getDefaultArgs() {
		return new Arguments();
	}

	@Override
	public Scope getScopeRequest() {
		return new Scope(true, true, true, true);
	}

	@Override
	public String getID() {
		return "Stamp";
	}

}
