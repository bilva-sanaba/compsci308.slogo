package parser;

import java.util.ArrayList;
import java.util.List;

import configuration.Arguments;
import model.Token;
import model.TokenType;

public class TokenNode implements SlogoNode {
	private Arguments children;
	private Token token;
	private List<String> params = null;
	//params not needed
	public TokenNode(Token token){
		this.children = new Arguments();
		this.params = new ArrayList<>();
		this.token=token;
	}
	
	public void addChild(Token arg){
		children.add(arg);;
	}	
	
	public void addList(ArrayList<String> params){
		this.params=params;
	}
	
	public Token getToken(){
		return token;
	}
	
	public Arguments getChildren(){
		return children;
	}

	@Override
	public TokenType getType() {
		// TODO Auto-generated method stub
		return null;
	}
}

