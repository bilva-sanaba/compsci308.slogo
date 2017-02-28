package model;

import configuration.Arguments;
import model.commands.CommandException;
import parser.SlogoNode;

/**
 * Inteprets Token tree formed by parser
 * 
 * @author DhruvKPatel
 *
 */
public class Intepreter {

	public Arguments evaluateTree(SlogoNode root) throws CommandException{
		Arguments returnTokens = new Arguments();
		
		for(SlogoNode node: root.getChildren()){
			if(node.getChildren().isEmpty()){
				returnTokens.append(node.getToken().evaluate(new Arguments()));
			}
			else{
				returnTokens.append(evaluateTree(node));
			}
		}
		
		return returnTokens;
	}

}
