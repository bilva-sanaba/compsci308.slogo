package model;

import model.commands.CommandException;
import parser.TokenNode;

/**
 * Inteprets Token tree formed by parser
 * 
 * @author DhruvKPatel
 *
 */
public class Interpreter {
	
	public Token evaluateTree(TokenNode root, Scope scope) throws CommandException{

		Arguments returnArgs = new Arguments();
		
		if(root.getToken().getType() == TokenType.LIST){
			TList list = (TList)root.getToken();
			list.setChildren(root.getChildren());
			return list.evaluate(returnArgs, getScopeFromRequest(root, scope));
		}
		
		for(TokenNode node : root.getChildren()){
			
			if(node.getChildren().isEmpty() && node.getToken().getType() != TokenType.LIST){
				returnArgs.add(node.getToken().evaluate(new Arguments(), getScopeFromRequest(node, scope)));
			}
			
			else{
				returnArgs.add(evaluateTree(node, scope));
			}
			
		}
	
		
		return root.getToken().evaluate(returnArgs, getScopeFromRequest(root, scope));
		
	}
	
	private Scope getScopeFromRequest(TokenNode node, Scope fullScope){
		return new Scope(fullScope, node.getToken().getScopeRequest());
	}
}
