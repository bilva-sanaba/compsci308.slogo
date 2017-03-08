package model;

import java.util.List;

import model.commands.CommandException;
import parser.tokenNodes.TokenNode;

public class Group implements Token {
	
	private List<TokenNode> children;
	
	@Override
	public TokenType getType() {
		return TokenType.LIST;
	}

	@Override
	public Token evaluate(Arguments args, Scope scope) throws CommandException {
		if(children == null) throw new CommandException("Group is empty");
		return this;
	}
	
	public String toString(){
		String s = "( ";
		for(TokenNode t: children){
			s += t.getToken().toString() + " ";
		}
		s += " )";
		return s;
	}

	public void setChildren(List<TokenNode> children) {
		this.children = children;
	}
	
	public List<TokenNode> getChildren(){
		return children;
	}
	
	/**
	 * Executes the children of the list node
	 *
	 * @return Token
	 * @throws CommandException 
	 */
	public Arguments executeChildren(Scope scope) throws CommandException{
		Arguments returns = new Arguments();
		
		Interpreter i = new Interpreter();
		for(TokenNode child: this.getChildren()){
			returns.add(i.evaluateTree(child, scope));
		}
		return returns;
	}

	@Override
	public Scope getScopeRequest() {
		return new Scope(true, true, true);
	}

}