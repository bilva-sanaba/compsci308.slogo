package model;

import model.commands.CommandException;
import parser.tokenNodes.TokenNode;

/**
 * Inteprets Token tree formed by parser
 * 
 * @author DhruvKPatel
 *
 */
public class Interpreter {
	
	public Token evaluateTree(TokenNode root, Scope scope) throws CommandException{
		root.getToken().setScope(new Scope(scope, root.getToken().getScopeRequest()));

		Arguments returnArgs = new Arguments();
		
		if(root.getToken().getType() == TokenType.LIST){
			TList list = (TList)root.getToken();
			list.setChildren(root.getChildren());
			return list.evaluate(returnArgs);
		}
		
		for(TokenNode node : root.getChildren()){
			
			if(node.getChildren().isEmpty() && node.getToken().getType() != TokenType.LIST){
				node.getToken().setScope(new Scope(scope, node.getToken().getScopeRequest()));
				returnArgs.add(node.getToken().evaluate(new Arguments()));
			}
			
			else{
				returnArgs.add(evaluateTree(node, scope));
			}
			
		}
	
		
		return root.getToken().evaluate(returnArgs);
		
	}
}
