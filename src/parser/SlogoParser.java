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
		TokenNode root = tNode;
		TokenNode parentNode = null;
		TokenNode head=root;
		int listCursor = 0;
		int stringCursor = 0; //EDIT
		
		for(int i=0; i<commandList.size(); i++){
			String word = commandList.get(i).trim();
			TokenNode tokenNode;
			System.out.println(word +", " + i);
			if(word.equals("[")){
				System.out.println("listCursor: " + listCursor);
				System.out.println("stringCursor: " + stringCursor);
				System.out.println("sub1: " + command.substring(0, stringCursor));
				System.out.println("sub2: " + command.substring(stringCursor));
				int startIndex = command.substring(0, stringCursor).length() + command.substring(stringCursor).indexOf("["); //puts to end of list //EDIT
				int startListIndex = i; // modify commandList
				System.out.println("si: " + startIndex);
				//System.out.println("sI: " + command.substring(cursor));
				//System.out.println("sListI: " + createSubList(cursor, commandList));
				
				int endIndex = getEndIndex(command.substring(startIndex)); //EDIT: startIndex
				
				System.out.println("endIndex: " + endIndex);
				
				ArrayList<String> subList = createSubList(startListIndex, commandList); //EDIT: list cursor
				
				System.out.println("sublist: " + subList);
				System.out.println("startListIndex: " + startListIndex);
				int endListIndex = getEndCursor(subList);
				
				System.out.println("endListIndex: " + endListIndex);

				i = startIndex + endIndex;
				
				tokenNode = parse(new TokenNode(root, new TList()), command.substring(startIndex + 1, i)); //EDIT: i
				
				i=endListIndex + startListIndex;
				
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
			listCursor++;
			stringCursor+=commandList.get(i).length() + 1; //EDIT
		}
		return head;
	}
	
	private ArrayList<String> createSubList(int cursor, ArrayList<String> commandList) {
		ArrayList<String> ans = new ArrayList<String>();
		for(int i = 0; i<commandList.size(); i++){ 
			if(i>=cursor){ //EDIT: i >= cursor
				//System.out.println(commandList.get(i));
				ans.add(commandList.get(i));
			}
		}
		return ans;
	}

	private int getEndIndex(String command) throws CommandException{
		Stack<String> stack = new Stack<String>();
		stack.push("[");
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
	
	private int getEndCursor(ArrayList<String> commandList) throws CommandException{
		Stack<String> stack = new Stack<String>();
		stack.push("[");
		for(int i = 1; i < commandList.size(); i++){
			if(commandList.get(i).equals("[")){
				stack.push(commandList.get(i));
			}
			else if(commandList.get(i).equals("]")){
				stack.pop();
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

