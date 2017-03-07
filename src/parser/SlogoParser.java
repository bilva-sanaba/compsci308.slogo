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
	
	public TokenNode parse(TokenNode tNode, String command) throws CommandException{
		ArrayList<String> commandList = fillList(command);
		System.out.println(commandList);
		TokenNode root = tNode;
		TokenNode parentNode = new TListNode(null, new TList());
		TokenNode head=root;
		int stringCursor = 0;
		
		for(int i=0; i<commandList.size(); i++){
			String word = commandList.get(i).trim();
			TokenNode tokenNode;

			if(word.equals("[")){

				int startIndex = command.substring(0, stringCursor).length() + command.substring(stringCursor).indexOf("["); //puts to end of list //EDIT
				int startListIndex = i; // modify commandList
				
				int endIndex = startIndex + getEndStringIndex(command.substring(startIndex), "[");
				
				ArrayList<String> subList = createSubList(startListIndex, commandList);
				
				int endListIndex = getEndListIndex(subList, "[");
				
				tokenNode = parse(new TListNode(root, new TList()), command.substring(startIndex + 1, endIndex));
				
				i=endListIndex + startListIndex;
				
			}
			else if(word.equals("(")){ //UNLIMITED PARAMETERS
				
				int startIndex = command.substring(0, stringCursor).length() + command.substring(stringCursor).indexOf("("); //puts to end of list //EDIT
				int startListIndex = i; // modify commandList
				
				int endIndex = startIndex + getEndStringIndex(command.substring(startIndex), "(");
				
				ArrayList<String> subList = createSubList(startListIndex, commandList);
				
				int endListIndex = getEndListIndex(subList, "(");
				
				String unlimitedParamCommand = commandList.get(i+1);
				
				tokenNode = factory.genTokenNode(root, unlimitedParamCommand);
				
				int numArgs = ((Command) tokenNode.getToken()).getNumArgs();
				int check=0;
				String subCommand="";
				
				String newCommand = command.substring(startIndex + 1, endIndex);
				ArrayList<String> newCommandList = fillList(newCommand);
				
				//FOR INFINITE ARGS
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
				//FOR INFINITE ARGS

				/*
				//FOR MULTIPLE ARGS 
				for(String s: newCommandList){
					System.out.println(s);
					System.out.println(check + ", " + numArgs);
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
				//FOR MULTIPLE ARGS
				 * *
				 */
				
				System.out.println(subCommand);
				
				//for loop adding tokenNodes for the ()
				
				
				tokenNode = parse(root, subCommand);
				
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

	private ArrayList<String> fillList(String command){
		command=command.trim();
		return new ArrayList<String>(Arrays.asList(command.split(" ")));
	}
	
	public void setLanguage(String language){
		factory.setLanguage(language);
	}
	
}