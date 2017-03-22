//This entire file is part of my masterpiece
//JACOB WEISS

package parser;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

import model.TokenType;
import model.commands.CommandFactory;
import model.exceptions.CommandException;
import model.tokens.Command;
import model.tokens.TList;
import parser.regularExpressions.ProgramParser;
import parser.tokenNodes.TListNode;
import parser.tokenNodes.TokenNode;
import parser.tokenNodes.TokenNodeFactory;

/**
 * The parser for user SLogo commands. We read the command and convert it into a tree
 * The tree is then consequently read in the model
 * @author Jacob Weiss
 * 
 * Masterpiece:
 * This code has been refactored in order to eliminate the repetitive code that was originally present.
 * By changing the parameters of the makeTree method and editing code within, I was able to reduce the length
 * of the code and improve efficiency. Notably, I was able to delete a 20-line method for finding the end index
 * in a String, which was very similar to the one used to find the end index in a List, but necessary with the old 
 * implementation. My code is roughly 30 lines shorter and infinitely more easy to follow.
 * 
 * In addition to this change, I fixed the implementation of parsing for unlimited parameters arguments so that 
 * a previously existent error was removed.This feature is now functional. In this section, the "Factory"
 * design pattern is used so that unlimited parameter commands may be added to the TokenNode tree.
 *
 */

public class SlogoParser {
	
	private TokenNodeFactory factory;
	private Map<String, String> startToEnd = new HashMap<String, String>();
	
	private ProgramParser parser = new ProgramParser();
	private CommandReformatter cReformat = new CommandReformatter();
	
	private final String LISTSTART = "ListStart";
	private final String LISTEND = "ListEnd";
	private final String GROUPSTART = "GroupStart";
	private final String GROUPEND = "GroupEnd";
	
	private final String SPACE = " ";
	
	public SlogoParser(CommandFactory commands){
		factory = new TokenNodeFactory(commands);
		cReformat = new CommandReformatter();
		parser = cReformat.getParser();
		startToEnd.put(LISTSTART, LISTEND);
		startToEnd.put(GROUPSTART, GROUPEND);
	}
	
	public TokenNode parse(String command) throws CommandException{
		command = cReformat.reformatCommand(command);
		return makeTree(new TokenNode(null, new TList()), fillList(command), false);
	}
	
	private TokenNode makeTree(TokenNode tNode, ArrayList<String> commandList, boolean unlimitedParam) throws CommandException{
		TokenNode root = tNode;
		TokenNode parentNode = new TListNode(null, new TList());
		TokenNode head=root;

		for(int i=0; i<commandList.size(); i++){
			String word = commandList.get(i).trim();
			String wordType = parser.getSymbol(word);
			TokenNode tokenNode;
			if(wordType.equals(LISTSTART) || wordType.equals(GROUPSTART)){
				int startListIndex = i;
				ArrayList<String> subList = createSubList(startListIndex, commandList);
				int endListIndex = getEndListIndex(subList, word);
				boolean unlimited;
				TokenNode newRoot;
				
				if(wordType.equals(LISTSTART)){
					unlimited = false;
					newRoot = new TListNode(root, new TList());
				}
				else{
					unlimited = true;
					newRoot = root;
				}
				tokenNode = makeTree(newRoot, makeNewCommandList(subList, endListIndex), unlimited);
				i = endListIndex + startListIndex;
			}
			else{
				tokenNode = factory.genTokenNode(root, word, unlimitedParam);
			}
			root.addChild(tokenNode);
			
			if(tokenNode.getToken().getType() == TokenType.COMMAND){
				parentNode=root;
				root=tokenNode;
			}
			
			if(root.getToken().getType() == TokenType.COMMAND && numArgsSatisfied(root)){
				Command rootCommand = (Command)root.getToken();
				String commandString = commandList.get(0);
				if(!(commandTakesInfiniteArgs(unlimitedParam, commandString))){
					root=parentNode;
					parentNode = root.getParent();
					if(unlimitedParam && i<commandList.size()-1 && !rootCommand.isNullCommand()){
						tokenNode = factory.genTokenNode(parentNode, commandString, unlimitedParam);
						root.addChild(tokenNode);
						parentNode=root; 
						root=tokenNode; 
					}
				}
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
	
	private int getEndListIndex(ArrayList<String> commandList, String t) throws CommandException{
		Stack<String> stack = new Stack<String>();
		stack.push(t);
		String end = startToEnd.get(parser.getSymbol(t));
		for(int i = 1; i < commandList.size(); i++){
			if(commandList.get(i).equals(t)){
				stack.push(commandList.get(i));
			}
			else if(parser.getSymbol(commandList.get(i)).equals(end)){
				stack.pop();
			}
			if(stack.isEmpty()){
				return i;
			}
		}
		throw new CommandException("List never closes");
	}
	
	private ArrayList<String> makeNewCommandList(ArrayList<String> subList, int endListIndex){
		ArrayList<String> result = new ArrayList<String>();
		for(int i=1; i< endListIndex; i++){
			result.add(subList.get(i));
		}
		return result;
	}
	
	private ArrayList<String> fillList(String command){
		command=command.trim();
		ArrayList<String> ansList = new ArrayList<String>(Arrays.asList(command.split(SPACE)));
		return removeEmptyElements(ansList);
	}
	
	private ArrayList<String> removeEmptyElements(ArrayList<String> ansList){
		ArrayList<String> result = new ArrayList<String>();
		for(String d : ansList){
			if(!d.isEmpty()){
				result.add(d);
			}
		}
		return result;
	}
	
	private boolean numArgsSatisfied(TokenNode root){
		Command rootCommand = (Command)root.getToken();
		return root.getChildren().size()==rootCommand.getNumArgs() && !rootCommand.hasUnlimitedArgs();
	}
	
	private boolean commandTakesInfiniteArgs(boolean unlimitedParam, String commandString){
		return unlimitedParam && factory.getInfiniteArgsCommands().contains(commandString);
	}
	
	public void setLanguage(String language){
		factory.setLanguage(language);
	}
}
