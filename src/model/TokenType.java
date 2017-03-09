package model;

/**
 * Holds all token types. 
 * 
 * @author DhruvKPatel
 */
public enum TokenType {
	VARIABLE("model.Variable"),
	LIST("model.TList"),
	COMMAND("model.Command"),
	CONSTANT("model.Constant");
	
	// COMMENT (not included because no functionality is needed)

	
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
