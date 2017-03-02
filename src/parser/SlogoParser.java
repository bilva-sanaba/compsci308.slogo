package parser;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Stack;

import model.Arguments;
import model.Command;
import model.Constant;
import model.TList;
import model.Token;
import model.TokenType;
import model.commands.CommandException;



/**
 * The parser for user SLogo commands. We read the command and convert it into a tree
 * @author Jacob Weiss
 *
 */

public class SlogoParser {
	
	public SlogoParser(){
	}
	
	public TokenNode parse(TokenNode tNode, String command) throws CommandException{
		
		ArrayList<String> commandList = fillList(command);
		
		TokenNode root = tNode;
		TokenNode parentNode = null;
		TokenNode head=root;
	
		
		for(int i=0; i<commandList.size(); i++){
			String word = commandList.get(i);
			TokenNode tokenNode;
			
			if(word.equals("[")){
				int startIndex = commandList.indexOf(("["));
				int endIndex = getEndIndex(commandList, startIndex);
				i = endIndex;
				tokenNode = parse(new TokenNode(root, new TList()), command.substring(startIndex, endIndex));
			}
			TokenNodeFactory factory = new TokenNodeFactory();
			tokenNode = factory.genTokenNode(parentNode, word); //will be global
			root.addChild(tokenNode);
			
			
			if(tokenNode.getToken().getType() == TokenType.COMMAND){
				parentNode=root;
				root=tokenNode;
			}
			
			if(root.getChildren().size()==((Command)root.getToken()).getNumArgs()){
				root=parentNode;
				parentNode=root.getParent();
			}		
		}
		return head;
	}
	
	private int getEndIndex(ArrayList<String> commandList, int startIndex) throws CommandException{
		Stack<String> stack = new Stack<String>();
		stack.push("[");
		
		for(int i = startIndex + 1; i < commandList.size(); i++){
			if(commandList.get(i).equals("[")){
				stack.push(commandList.get(i));
			}
			else if(commandList.get(i).equals("]")){
				stack.pop();
			}
			else if(stack.isEmpty()){
				return i;
			}
		}
		throw new CommandException("List never closes");
	}

	private ArrayList<String> fillList(String command){
		return new ArrayList<String>(Arrays.asList(command.split(" ")));
	}
	
}

