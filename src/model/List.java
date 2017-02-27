package model;

public class List implements Token {
	
	@Override
	public TokenType getType() {
		return TokenType.LIST;
	}

}
