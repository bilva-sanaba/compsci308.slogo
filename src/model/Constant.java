package model;

/**
 * This class is basically an Integer.
 * It is used to define an integer as a 
 * Token in arguments.
 * 
 * @author DhruvKPatel
 *
 */
public class Constant implements Token {
	
	private double i;
	
	public Constant(double i){
		this.i = i;
	}
	
	public double getVal(){
		return i;
	}
	
	/**
	 * Defines Token Type
	 */
	@Override
	public TokenType getType() {
		return TokenType.CONSTANT;
	}
}
