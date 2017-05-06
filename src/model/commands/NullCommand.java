package model.commands;

import model.Token;
import model.TokenType;
import model.configuration.Arguments;
import model.configuration.Scope;
import model.exceptions.CommandException;
import model.tokens.Command;

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
	
	public boolean isNullCommand(){
		return true;
	}

	@Override
	public boolean hasUnlimitedArgs() {
		return false;
	}

}
