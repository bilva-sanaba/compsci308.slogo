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
	
	private int i;
	
	public Constant(int i){
		this.i = i;
	}
	
	public int getVal(){
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
