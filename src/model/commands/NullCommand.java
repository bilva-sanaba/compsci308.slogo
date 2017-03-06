package model.commands;

import model.Arguments;
import model.Scope;

/**
 * Command that does nothing
 * @author DhruvKPatel
 *
 */
public class NullCommand extends AbstractCommand {
	private String id;
	
	/**
	 * Constructs a null command with certain ID
	 * @param id
	 */
	public NullCommand(String id){
		this.id = id;
	}
	
	@Override
	public double execute(Arguments args, Scope scope) throws CommandException {
		return 0;
	}

	@Override
	public Arguments getDefaultArgs() {
		return new Arguments();
	}

	@Override
	public Scope getScopeRequest() {
		return new Scope(false, false, false);
	}

	@Override
	public String getID() {
		return id;
	}

}
