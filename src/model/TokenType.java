package model;

/**
 * Holds all token types. 
 * 
 * @author DhruvKPatel
 */
public enum TokenType {
//	VARIABLE
//	LIST
	COMMAND("model.Command.java"),
	CONSTANT("model.Constant.java");

	
	private Class<? extends Token> enclosingClass;
	
	TokenType(String className){
		try {
			Class<?> c = Class.forName(className);
			enclosingClass = c.asSubclass(Token.class);
		} catch (ClassNotFoundException e) {
			enclosingClass = Token.class;
		}
	}
	
	public Class<? extends Token> getTokenClass(){
		return enclosingClass;
	}
	
}
