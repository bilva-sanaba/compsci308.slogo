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
import model.commands.CommandFactory;
import parser.tokenNodes.TListNode;
import parser.tokenNodes.TokenNode;
import parser.tokenNodes.TokenNodeFactory;

/**
 * The parser for user SLogo commands. We read the command and convert it into a tree
 * @author Jacob Weiss
 *
 */

public class SlogoParser {
	
	private TokenNodeFactory factory;
	
	public SlogoParser(CommandFactory commands){
		factory = new TokenNodeFactory(commands);
	}
	
	public TokenNode parse(TokenNode tNode, String command, boolean unlimitedParam) throws CommandException{
		ArrayList<String> commandList = fillList(command);
		TokenNode root = tNode;
		TokenNode parentNode = new TListNode(null, new TList());
		TokenNode head=root;
		int stringCursor = 0;
		
		
		for(int i=0; i<commandList.size(); i++){
			String word = commandList.get(i).trim();
			TokenNode tokenNode;

			if(word.equals("[") || word.equals("(")){
				int startIndex = command.substring(0, stringCursor).length() + command.substring(stringCursor).indexOf(word); //puts to end of list //EDIT
				int startListIndex = i; // modify commandList
				
				int endIndex = startIndex + getEndStringIndex(command.substring(startIndex), word);
				
				ArrayList<String> subList = createSubList(startListIndex, commandList);
				
				int endListIndex = getEndListIndex(subList, word);
				
				boolean unlimited;
				
				if(word.equals("[")){
					
					unlimited = false;
					
				}
				else{
					
					unlimited = true;
					
				}
				tokenNode = parse(new TListNode(root, new TList()), command.substring(startIndex + 1, endIndex), unlimited);
				
				i = endListIndex + startListIndex;
			}
			else{
				tokenNode = factory.genTokenNode(parentNode, word, unlimitedParam); //will be global
			}
			System.out.println(tokenNode.getToken().getType() + " , " );
			System.out.println(tokenNode.getChildren() == null);
			root.addChild(tokenNode);
			
			if(tokenNode.getToken().getType() == TokenType.COMMAND){
				parentNode=root;
				root=tokenNode;
			}
			
			if(!unlimitedParam && root.getToken().getType() == TokenType.COMMAND && root.getChildren().size()==((Command)root.getToken()).getNumArgs()){
				root=parentNode;
				parentNode=root.getParent();
			}
			
			stringCursor+=commandList.get(i).length() + 1; //add 1 for the space character
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

	private int getEndStringIndex(String command, String t) throws CommandException{
		Stack<String> stack = new Stack<String>();
		stack.push(t);
		System.out.println(t);
		System.out.println("String: " + command);
		for(int i = 1; i < command.length(); i++){
			if(command.substring(i, i+1).equals(t)){
				stack.push(command.substring(i, i+1));
			}
			else if(command.substring(i, i+1).equals("]") || command.substring(i, i+1).equals(")")){
				stack.pop();
			}
			if(stack.isEmpty()){
				return i;
			}
		}
		throw new CommandException("List never closes");
	}
	
	private int getEndListIndex(ArrayList<String> commandList, String t) throws CommandException{
		Stack<String> stack = new Stack<String>();
		stack.push(t);
		System.out.println(t);
		System.out.println("List: " + commandList);
		for(int i = 1; i < commandList.size(); i++){
			if(commandList.get(i).equals(t)){
				stack.push(commandList.get(i));
			}
			else if(commandList.get(i).equals("]") || commandList.get(i).equals(")")){
				stack.pop();
			}
			if(stack.isEmpty()){
				return i;
			}
		}
		throw new CommandException("List never closes");
	}
	
	private String getReformatCommandForUnlimited(ArrayList<String> newCommandList, String unlimitedParamCommand){
		int check = 0;
		String subCommand = "";
		for(String s: newCommandList){
			if(check == 0){
				for(int de=0; de<newCommandList.size()-2; de++){
					subCommand = subCommand + " " + unlimitedParamCommand;
				}
				check++;
			}
			else{
				subCommand = subCommand + " " + s;
			}
		}
		return subCommand;
	}
	
	private String getReformatCommandForSpecified(ArrayList<String> newCommandList, String unlimitedParamCommand, int numArgs){
		int check = 0;
		String subCommand = "";
		for(String s: newCommandList){
			if(check == 0){
				subCommand = subCommand + " " + unlimitedParamCommand;
				check++;
			}
			if(!s.equals(unlimitedParamCommand)){
				subCommand = subCommand + " " + s;
				check++;
			}
			if(check > numArgs){
				check = 0;
			}
		}
		return subCommand;
	}

	private ArrayList<String> fillList(String command){
		command=command.trim();
		return new ArrayList<String>(Arrays.asList(command.split(" ")));
	}
	
	public void setLanguage(String language){
		factory.setLanguage(language);
	}
	
}