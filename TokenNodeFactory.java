//This entire file is part of my masterpiece
//JACOB WEISS
package parser.tokenNodes;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;


import model.TokenType;

import model.commands.CommandFactory;
import model.commands.NullCommand;
import model.exceptions.CommandException;
import model.tokens.Command;
import model.tokens.Constant;
import model.tokens.Variable;
import parser.regularExpressions.ProgramParser;
/**
 * Determines the type of tokenNode that is added
 * checks for null command that follows TO
 * error checking for syntax and undefined commands
 * 
 * Masterpiece:
 * Checking for unlimited parameters command used in masterpiece
 * @author Jacob Weiss
 *
 */
public class TokenNodeFactory {
	
	private static ResourceBundle languageResourceBundle;
	
	public static final String DEFAULT_RESOURCES_PACKAGE = "resources.languages/";
	public static final String SYNTAX = "Syntax";
	private String language = "English";
	
	private static final String COMMAND = "Command";
	private static final String VARIABLE = "Variable";
	private static final String CONSTANT = "Constant";
	
	private String[] infiniteArgsCommands = {"Sum", "Difference" , "Product" , "Quotient" , "Remainder" , "Power"};
	private final String UNLIMITED = "Unlimited";
	
	private ProgramParser parser = new ProgramParser();
	
	private static List<String> possibleCommands = new ArrayList<String>();
	private static Map<String, ArrayList<String>> keyMap = new HashMap<String, ArrayList<String>>();
	
	private CommandFactory cFactory;
	
	public TokenNodeFactory(CommandFactory commands){
		cFactory = commands;
	}
	
	private void createValueList(){
		languageResourceBundle = ResourceBundle.getBundle(DEFAULT_RESOURCES_PACKAGE + language);
		Enumeration<String> resourceKeys = languageResourceBundle.getKeys();
		while(resourceKeys.hasMoreElements()){
			String key = resourceKeys.nextElement();
			String value = languageResourceBundle.getString(key);
			ArrayList<String> valueList = new ArrayList<String>(Arrays.asList(value.split("\\|")));
			keyMap.put(key, valueList);
			for(String v: valueList){
				possibleCommands.add(v);
			}
		}
	}
	
	public TokenNode genTokenNode(TokenNode parentNode, String word, boolean unlimitedParam) throws CommandException{
		parser.addPatterns(DEFAULT_RESOURCES_PACKAGE + SYNTAX);
		createValueList();
		TokenNode tokenNode = new TokenNode(parentNode, null);
		String type = parser.getSymbol(word);
		ArrayList<String> infiniteArgsCommandList = getInfiniteArgsCommands();
			if(type.equals(COMMAND)){
				if(possibleCommands.contains(word)){
					String wordID = findWordID(word);
					//check if unlimParam and wordID is sum, diff, etc.
					if(unlimitedParam && infiniteArgsCommandList.contains(wordID)){
						wordID = UNLIMITED + wordID;
					}
					Command t = cFactory.getCommand(wordID);
					tokenNode = new CommandNode(parentNode, t);
				}
				else if(cFactory.containsRuntimeCommand(word)){
					tokenNode = new CommandNode(parentNode, cFactory.getCommand(word));
				}
				else{
					if(parentNode.getToken().getType()==TokenType.COMMAND && ((Command)parentNode.getToken()).getID().equals("To")){
						tokenNode = new CommandNode(parentNode, new NullCommand(word));
					}
					else{
						throw new CommandException(String.format("'%s' command is undefined.", word));
					}
				}
			}
			else if(type.equals(VARIABLE)){
				tokenNode = new VariableNode(parentNode, new Variable(word.substring(1)));
			}
			else if(type.equals(CONSTANT)){
				tokenNode = new ConstantNode(parentNode, new Constant(Double.parseDouble(word)));
			}
			else{
				throw new CommandException(String.format("Improper syntax: %s is not valid (may need to fix spacing)", word));
			}
			return tokenNode;
	}

	private String findWordID(String word){
		for(String key: keyMap.keySet()){
			if(keyMap.get(key).contains(word)){
				return key;
			}
		}
		return "";
	}
	
	public void setLanguage(String language){
		this.language = language;
	}
	
	public ArrayList<String> getInfiniteArgsCommands(){
		return new ArrayList<String>(Arrays.asList(infiniteArgsCommands));
	}
}


