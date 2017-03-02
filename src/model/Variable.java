package model;

import model.commands.CommandException;

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
	public Token evaluate(Arguments args) {
		return this;
	}
	
	public Constant getValue(){

		System.out.println(vars.get(this) + "---");
		return vars.get(this);

	}
	
	/**
	 * Returns variable for error messages
	 */
	public String toString(){
		return String.format(":%s", getName());
	}
	
//	/**
//	 * Variables with same name are equal in a set.
//	 */
//	@Override
//	public int hashCode(){
//		return this.name.hashCode();
//	}

	@Override
	public void setScope(Scope s) {
		vars = s.getVariables();
	}
	
	@Override
	public Scope getScopeRequest() {
		return new Scope(false, true, false);
	}

}
