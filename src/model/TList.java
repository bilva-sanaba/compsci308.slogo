package model;

import java.util.List;

import model.commands.CommandException;
import parser.TokenNode;

public class TList implements Token {
	Scope myScope;
	
	private List<TokenNode> children;
	
	@Override
	public TokenType getType() {
		return TokenType.LIST;
	}

	@Override
	public Token evaluate(Arguments args) throws CommandException {
		if(children == null) throw new CommandException("List is empty");
		return this;
	}
	
	public String toString(){
		String s = "[ ";
		for(TokenNode t: children){
			s += t.getToken().toString() + " ";
		}
		s += " ]";
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
	public Token executeChildren() throws CommandException{
		Interpreter i = new Interpreter();
		Token ans = new Constant(0);
		for(TokenNode child: this.getChildren()){
			ans = i.evaluateTree(child, myScope);
		}
		return ans;
	}
	
	

	@Override
	public void setScope(Scope s) {
		myScope = s; 
	}

	@Override
	public Scope getScopeRequest() {
		return new Scope(true, true, true);
	}

}
