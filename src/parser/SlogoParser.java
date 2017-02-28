package parser;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Stack;

import model.Arguments;
import model.Constant;
import model.Token;
import model.commands.CommandException;



/**
 * The parser for user SLogo commands. We read the command and convert it into a tree
 * @author jwei528
 *
 */

public class SlogoParser {
	//private static ArrayList<TokenNode> nodeList = new ArrayList<TokenNode>();
	
	public SlogoParser(){
	}
	
	public TokenNode parse(String command) throws CommandException{
		
		ArrayList<String> commandList = fillList(command);
		
		TokenNode root = new TokenNode(new List()));
		TokenNode head=root;
	
		
		for(int i=0; i<commandList.size(); i++){
			String word = commandList.get(i);
			TokenNode tokenNode;
			TokenNodeFactory factory = new TokenNodeFactory();
			tokenNode = factory.genTokenNode(word);
			
			if(word.equals("[")){
				int startIndex = commandList.indexOf(("["));
				int endIndex = getEndIndex(commandList, startIndex);
				i = endIndex;
				tokenNode = parse(command.substring(startIndex, endIndex));
			}
			
			root.addChild(tokenNode);
			if(tokenNode.getToken().getType().equals("COMMAND")){ 
				root=tokenNode;
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

