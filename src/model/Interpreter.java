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
			
			if(node.getToken().getType() == TokenType.LIST){
				TList list = (TList)node.getToken();
				list.setChildren(node.getChildren());
				returnArgs.add(list);
			}
			
			else if(node.getChildren().isEmpty()){
				returnArgs.add(node.getToken().evaluate(new Arguments()));
			}
			
			else{
				returnArgs.add(evaluateTree(node, scope));
			}
			
		}
		return root.getToken().evaluate(returnArgs);
		
	}
}
