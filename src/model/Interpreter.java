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
		
	
		for(TokenNode node : root.getChildren()){
			
			if(node.getChildren().isEmpty()){
				returnArgs.add(node.getToken().evaluate(new Arguments()));
			}
			
			else{
				returnArgs.add(evaluateTree(node, scope));
			}
			
		}
	
		if(root.getToken().getType() == TokenType.LIST){
			TList list = (TList)root.getToken();
			list.setChildren(root.getChildren());
		}
		
		return root.getToken().evaluate(returnArgs);
		
	}
}
