package parser;

import java.util.ArrayList;
import java.util.List;

import model.Arguments;
import model.Token;
import model.TokenType;
import model.commands.CommandException;

public class TokenNode{
	private Token token;
	private List<TokenNode> children;

	public TokenNode(Token token){
		this.children = new ArrayList<TokenNode>();
		this.token = token;
	}
	
	public void addChild(TokenNode child){
		children.add(child);
	}
	
	public void addChild(Token childVal){
		children.add(new TokenNode(childVal));
	}	
	
	public Token getToken(){
		return token;
	}
	
	public List<TokenNode> getChildren(){
		return children;
	}

}

