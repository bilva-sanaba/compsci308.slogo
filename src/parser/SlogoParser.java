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
	
	private TokenNodeFactory factory = new TokenNodeFactory();
	
	public SlogoParser(){
	}
	
	public TokenNode parse(TokenNode tNode, String command) throws CommandException{
		ArrayList<String> commandList = fillList(command);
		//System.out.println(commandList);
		TokenNode root = tNode;
		TokenNode parentNode = null;
		TokenNode head=root;
		
		for(int i=0; i<commandList.size(); i++){
			//System.out.println(commandList); //MAYBE NUMARGS??
			String word = commandList.get(i).trim();
			TokenNode tokenNode;
			System.out.println(word);
			if(word.equals("[")){
				
				int startIndex = command.indexOf("["); //puts to end of list
				int cursor = commandList.indexOf("["); // modify commandList
				//System.out.println(startIndex + "," + cursor);
				
				
				int endIndex = getEndIndex(command.substring(startIndex), startIndex);
				//System.out.println(endIndex);
				
				ArrayList<String> subList = createSubList(cursor, commandList);
				//System.out.println(subList);
				
				int endCursor = getEndCursor(subList, cursor);
				System.out.println("ec: " + endCursor);
				i = startIndex + endIndex;
				
				System.out.println("slist:" + command.substring(startIndex+1, i));
				tokenNode = parse(new TokenNode(root, new TList()), command.substring(startIndex + 1, i));
				
				//System.out.println(endCursor);
				System.out.println("i: " + i);
				i=endCursor+2;
				System.out.println("i: " + i);
				System.out.println("cnew:" + commandList.get(i));
				
			}
			else{
				tokenNode = factory.genTokenNode(parentNode, word); //will be global
			}
			root.addChild(tokenNode);
			
			
			
			if(tokenNode.getToken().getType() == TokenType.COMMAND){
				parentNode=root;
				root=tokenNode;
			}
			
			if(root.getToken().getType() == TokenType.COMMAND && root.getChildren().size()==((Command)root.getToken()).getNumArgs()){
				root=parentNode;
				parentNode=root.getParent();
			}		
		}
		return head;
	}
	
	private ArrayList<String> createSubList(int cursor, ArrayList<String> commandList) {
		ArrayList<String> ans = new ArrayList<String>();
		for(int i = 0; i<commandList.size(); i++){
			if(i>=cursor){
				ans.add(commandList.get(i));
			}
		}
		return ans;
	}

	private int getEndIndex(String command, int startIndex) throws CommandException{
		Stack<String> stack = new Stack<String>();
		stack.push("[");
		//System.out.println(command);
		for(int i = 1; i < command.length(); i++){
			if(command.substring(i, i+1).equals("[")){
				stack.push(command.substring(i, i+1));
			}
			else if(command.substring(i, i+1).equals("]")){
				stack.pop();
			}
			if(stack.isEmpty()){
				return i;
			}
		}
		throw new CommandException("List never closes");
	}
	
	private int getEndCursor(ArrayList<String> commandList, int cursor) throws CommandException{
		Stack<String> stack = new Stack<String>();
		stack.push("[");
		//System.out.println(commandList);
		for(int i = 1; i < commandList.size(); i++){
			if(commandList.get(i).equals("[")){
				stack.push(commandList.get(i));
			}
			else if(commandList.get(i).equals("]")){
				stack.pop();
				//System.out.println("x");
			}
			if(stack.isEmpty()){
				return i;
			}
		}
		throw new CommandException("List never closes");
	}

	private ArrayList<String> fillList(String command){
		command=command.trim();
		return new ArrayList<String>(Arrays.asList(command.split(" ")));
	}
	
	public void setLanguage(String language){
		factory.setLanguage(language);
	}
	
}

