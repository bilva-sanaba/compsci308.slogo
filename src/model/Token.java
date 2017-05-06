package model;

import model.configuration.Arguments;
import model.configuration.Scope;
import model.exceptions.CommandException;

/**
 * This is a very small interface used to encapsulate all Commands, Constants, Variables, and Lists.
 * It only purpose is to ensure that every token has knowledge of its type at all times.
 * Also, it will be used as the type of object for arguments.
 * @author DhruvKPatel
 *
 */
public interface Token {
	
	/**
	 * Returns type of Token.
	 */
	public TokenType getType();
	
	/**
	 * Evaluates Token given its arguments.
	 * 
	 * Throws error if arguments don't match up.
	 * @throws CommandException 
	 */
	public Token evaluate(Arguments args, Scope scope) throws CommandException;
	
	/**
	 * Describes token for error messages
	 */
	public String toString();
	
	
	/**
	 * Asks which information it will need
	 */
	public Scope getScopeRequest();
}

