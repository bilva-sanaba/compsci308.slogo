package parser;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

import model.Command;
import model.TList;
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
	private Map<String, String> startToEnd = new HashMap<String, String>();
	
	private final String SPACE = " ";
	
	public SlogoParser(CommandFactory commands){
		factory = new TokenNodeFactory(commands);
		startToEnd.put("[", "]");
		startToEnd.put("(", ")");
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
				int startListIndex = i;
				int endIndex = startIndex + getEndStringIndex(command.substring(startIndex), word);
				ArrayList<String> subList = createSubList(startListIndex, commandList);
				int endListIndex = getEndListIndex(subList, word);
				boolean unlimited;
				TokenNode newRoot;
				
				if(word.equals("[")){
					unlimited = false;
					newRoot = new TListNode(root, new TList());
					System.out.println("x ");
				}
				else{
					unlimited = true;
					newRoot = root;
				}
				System.out.println("startIndex +1 : "  + (startIndex + 1));
				System.out.println("endIndex: " + endIndex);
				System.out.println("parsing: " + command.substring(startIndex+1, endIndex));
				tokenNode = parse(newRoot, command.substring(startIndex + SPACE.length(), endIndex), unlimited);
				i = endListIndex + startListIndex;
			}
			else{
				tokenNode = factory.genTokenNode(parentNode, word, unlimitedParam); //will be global
			}
			root.addChild(tokenNode);
			
			//MOVED DOWN
			if(tokenNode.getToken().getType() == TokenType.COMMAND){
				parentNode=root;
				root=tokenNode;
			}
			
			if(root.getToken().getType() == TokenType.COMMAND && root.getChildren().size()==((Command)root.getToken()).getNumArgs()){
				String commandString = commandList.get(0);
				boolean nullCommand = ((Command)root.getToken()).isNullCommand();
				System.out.println("commandString: " + commandString);
				System.out.println("root : " + ((Command)root.getToken()).getID());
				System.out.println("parentNode : " + parentNode);
				System.out.println("current:" + commandList.get(i));
				if(!unlimitedParam || !factory.getInfiniteArgsCommands().contains(commandString)){
					root=parentNode;
					parentNode=root.getParent();
					if(unlimitedParam && i<commandList.size()-1 && !nullCommand){
						tokenNode = factory.genTokenNode(parentNode, commandString, unlimitedParam);
						root.addChild(tokenNode);
						parentNode=root; //ADD
						root=tokenNode; //ADD
					}
				}
			}		
			
			System.out.println("word : " + word);
			stringCursor+=commandList.get(i).length() + SPACE.length(); //add 1 for the space character
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
			else if(command.substring(i, i+1).equals(startToEnd.get(t))){
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
			else if(commandList.get(i).equals(startToEnd.get(t))){
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
		return new ArrayList<String>(Arrays.asList(command.split(SPACE)));
	}
	
	public void setLanguage(String language){
		factory.setLanguage(language);
	}
}