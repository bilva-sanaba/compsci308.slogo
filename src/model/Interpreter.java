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
	
	public Token evaluateTree(TokenNode root) throws CommandException{
		
		Arguments returnArgs = new Arguments();
		
		for(TokenNode node : root.getChildren()){
			
			if(node.getChildren().isEmpty()){
				returnArgs.add(node.getToken().evaluate(new Arguments()));
			}
			else{
				returnArgs.add(evaluateTree(node));
			}
			
		}
		return root.getToken().evaluate(returnArgs);
		
	}
}
