package model;

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
}
