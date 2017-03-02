package model;

/**
 * Token: Constant
 * This class is basically an Integer.
 * It is used to define an integer as a 
 * Token in arguments.
 * 
 * @author DhruvKPatel
 *
 */
public class Constant implements Token {
	
	private double value;
	
	public Constant(double value){
		this.value = value;
	}
	
	public double getVal(){
		return value;
	}
	
	/**
	 * Defines Token Type
	 */
	@Override
	public TokenType getType() {
		return TokenType.CONSTANT;
	}

	@Override
	public Token evaluate(Arguments args) {
		return this;
	}
	
	public String toString(){
		return String.valueOf(value);
	}

	@Override
	public void setScope(Scope s) {
		// Constants don't need scope
	}

	@Override
	public Scope getScopeRequest() {
		return new Scope(false, false, false);
	}
	
	
}
