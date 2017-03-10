package model.tokens;

import model.Token;
import model.TokenType;
import model.configuration.Arguments;
import model.configuration.Scope;
import model.exceptions.CommandException;

/**
 * This class is for variables, to keep track of names/values.
 * 
 * @author DhruvKPatel
 */
public class Variable implements Token {
	private String name;
	private VariableContainer vars;

	/**
	 * Constructs a variable with no value (Defaults to 0)
	 */
	public Variable(String name){
		this.name = name;
	}
	
	/**
	 * Returns variable name
	 */
	public String getName(){
		return name;
	}
	
	/**
	 * Defines token type
	 */
	@Override
	public TokenType getType() {
		return TokenType.VARIABLE;
	}

	@Override
	public Token evaluate(Arguments args, Scope scope) throws CommandException {
		vars = scope.getVariables();
		return this;
	}
	
	public Constant getValue() throws CommandException{
		return vars.get(this);
	}
	
	/**
	 * Returns variable for error messages
	 */
	public String toString(){
		return String.format(":%s", getName());
	}
	
	@Override
	public Scope getScopeRequest() {
		return new Scope(false, true, false, false);
	}

}
