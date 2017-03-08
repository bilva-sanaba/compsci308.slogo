package model.commands;

import model.Arguments;
import model.Command;
import model.Scope;
import model.Token;
import model.TokenType;

/**
 * Command that does nothing
 * @author DhruvKPatel
 *
 */
public class NullCommand implements Command {
	private String id;
	
	/**
	 * Constructs a null command with certain ID
	 * @param id
	 */
	public NullCommand(String id){
		this.id = id;
	}

	@Override
	public Scope getScopeRequest() {
		return new Scope(false, false, false, false);
	}

	@Override
	public String getID() {
		return id;
	}

	@Override
	public TokenType getType() {
		return TokenType.COMMAND;
	}

	@Override
	public Token evaluate(Arguments args, Scope scope) throws CommandException {
		return this;
	}

	@Override
	public int getNumArgs() {
		return 0;
	}

}
