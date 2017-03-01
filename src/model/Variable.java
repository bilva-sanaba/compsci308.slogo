package model;

/**
 * This class is for variables, to keep track of names/values.
 * 
 * @author DhruvKPatel
 */
public class Variable implements Token {

	private final String name;
	private Constant value;
	
	/**
	 * Constructs a variable with a name and value
	 * @param name 
	 * @param value
	 */
	public Variable(String name, Constant value){
		this.name = name;
		this.value = value;
	}
	
	/**
	 * Constructs a variable with no value (Defaults to 0)
	 */
	public Variable(String name){
		this(name, new Constant(0));
	}
	
	/**
	 * Returns variable name
	 */
	public String getName(){
		return name;
	}
	
	/**
	 * Returns variable value
	 */
	public Constant getValue(){
		return value;
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
		return this.value;
	}
	
	/**
	 * Returns variable for error messages
	 */
	public String toString(){
		return String.format(":%s(%f)", getName(), getValue());
	}
	
	/**
	 * Variables with same name are equal in a set.
	 */
	@Override
	public int hashCode(){
		return this.name.hashCode();
	}

}
