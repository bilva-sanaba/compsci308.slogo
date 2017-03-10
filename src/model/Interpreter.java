package model;

import java.util.Map;

import model.configuration.Arguments;
import model.configuration.Scope;
import model.exceptions.CommandException;
import model.tokens.TList;
import parser.tokenNodes.TokenNode;

/**
 * Inteprets Token tree formed by parser
 * 
 * @author DhruvKPatel
 *
 */
public class Interpreter {
//	private World world;
//	
//	public Interpreter(World world){
//		this.world = world;
//	}
//	
//	public Arguments evaluateForAllTurtles(TokenNode root, Scope scope) throws CommandException{
//		Arguments returnArgs = new Arguments();
//		
//		if(root.getToken().getScopeRequest().getTrajectory() == null){
//			returnArgs.add(this.evaluateTree(root, scope));
//		}
//		else{
//			for(Turtle t : scope.getwo){
//				scope.getWorld().
//				
//				Scope singleTurtleScope = new Scope(scope.getCommands(), scope.getVariables(), t.getTrajectory(), scope.getWorld(), scope);
//				returnArgs.add(this.evaluateTree(root, singleTurtleScope));
//			}
//		}
//		return returnArgs; // Returns answer to last turtle's evaluation
//		
//	}

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
	
	
	private Scope getScopeFromRequest(TokenNode node, Scope fullScope) throws CommandException{
		return new Scope(fullScope, node.getToken().getScopeRequest());
	}
}
