package model.tokens;

import java.util.ArrayList;
import java.util.List;

import model.Interpreter;
import model.Token;
import model.TokenType;
import model.configuration.Arguments;
import model.configuration.Scope;
import model.exceptions.CommandException;
import parser.tokenNodes.TokenNode;

/**
 * This token is a container for all lists. 
 * 
 * When during the evaluation phase, the list stores the arguments it recieves.
 * Later on, you can choose to evaluate the list's contents by calling "evaluateChildren"
 * @author DhruvKPatel
 *
 */
public class TList implements Token {
	
	private List<TokenNode> children;
	
	@Override
	public TokenType getType() {
		return TokenType.LIST;
	}

	@Override
	public Token evaluate(Arguments args, Scope scope) throws CommandException {
		if(children == null) throw new CommandException("List is empty");
		return this;
	}
	
	/**
	 * Evaluates contents of list and sets this evaluation as new contents
	 * @param args
	 * @param scope
	 * @throws CommandException
	 */
	public TList getSimplifiedList(Arguments args, Scope scope) throws CommandException{
		List<TokenNode> newChildren = new ArrayList<>();
		for(Token t: evaluateContents(scope)){
			newChildren.add(new TokenNode(null, t));
		}
		TList newList = new TList();
		newList.setChildren(newChildren);
		return newList;
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
	public Arguments evaluateContents(Scope scope) throws CommandException{
		Arguments returns = new Arguments();
		
		Interpreter i = new Interpreter();

		for(TokenNode child: this.getChildren()){
			returns.add(i.evaluateTree(child, scope));
		}
		return returns;
	}

	@Override
	public Scope getScopeRequest() {
		return new Scope(true, true, true, true);
	}

}