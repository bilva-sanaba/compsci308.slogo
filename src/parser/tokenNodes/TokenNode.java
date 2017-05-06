package parser.tokenNodes;

import java.util.ArrayList;
import java.util.List;

import model.Token;
import model.TokenType;
import model.configuration.Arguments;
import model.exceptions.CommandException;
/**
 * 
 * @author Jacob Weiss
 *
 */
public class TokenNode implements TokenNodeInterface{
	private Token token;
	private List<TokenNode> children;
	private TokenNode parentNode;

	public TokenNode(TokenNode parentNode, Token token){
		this.children = new ArrayList<TokenNode>();
		this.token = token;
		this.parentNode = parentNode;
	}
	
	public void addChild(TokenNode child){
		children.add(child);
	}
	
	public void addChild(Token childVal){
		children.add(new TokenNode(this, childVal));
	}	
	
	public Token getToken(){
		return token;
	}
	
	public List<TokenNode> getChildren(){
		return children;
	}
	
	public TokenNode getParent(){
		return parentNode;
	}

}

