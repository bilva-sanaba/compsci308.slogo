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
		Arguments returnTokens = new Arguments();
		System.out.print(root.getToken() + "(");
		for(TokenNode node: root.getChildren()){
			
			if(node.getChildren().isEmpty()){
				System.out.print(node.getToken() + ",");
				Token ans = node.getToken().evaluate(new Arguments());
				returnTokens.add(ans);
			}
			else{
				returnTokens.add(evaluateTree(node));
			}
		}
		System.out.print(")");
		
//		System.out.println("\n\n");
//		if(returnTokens.numArgs() > 1) throw new CommandException(String.format("Too many arguments: %s unused", returnTokens.get(1).toString()));
		return returnTokens.get(0);
	}

}
