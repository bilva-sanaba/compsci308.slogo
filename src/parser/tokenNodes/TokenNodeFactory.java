package parser.tokenNodes;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.regex.Pattern;

import model.Command;
import model.Constant;
import model.TList;
import model.Token;
import model.Variable;
import model.commands.CommandException;
import model.commands.CommandFactory;
import model.commands.NullCommand;
import parser.regularExpressions.ProgramParser;
/**
 * 
 * @author Jacob Weiss
 *
 */
public class TokenNodeFactory {
	

	private static ResourceBundle languageResourceBundle;
	private static ResourceBundle syntaxResourceBundle;
	
	public static final String DEFAULT_RESOURCES_PACKAGE = "resources.languages/";
	
	public static final String SYNTAX = "Syntax";
	private String language = "English";

	private ProgramParser parser = new ProgramParser();
	
	private static List<String> possibleCommands = new ArrayList<String>();
	private static Map<String, ArrayList<String>> keyMap = new HashMap<String, ArrayList<String>>();
	
	
	
	public TokenNodeFactory(){
	}
	
	private void createValueList(){
		//may need try and catch
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
	

	public TokenNode genTokenNode(TokenNode parentNode, String word) throws CommandException{
		parser.addPatterns(DEFAULT_RESOURCES_PACKAGE + SYNTAX);
		createValueList();
		TokenNode tokenNode = new TokenNode(parentNode, null);
		String type = parser.getSymbol(word);
		System.out.println(word + ", " + type);
			if(type.equals("Command")){//word is in resources
				if(possibleCommands.contains(word)){
					String wordID = findWordID(word);
					CommandFactory cFactory = new CommandFactory();
					Command t = cFactory.getCommand(wordID);
					tokenNode = new CommandNode(parentNode, t);
				}
				else{
					tokenNode = new CommandNode(parentNode, new NullCommand(word));
				}
			}
			else if(type.equals("Variable")){ //include : check
				tokenNode = new VariableNode(parentNode, new Variable(word.substring(1)));
			}
			else if(type.equals("Constant")){
				tokenNode = new ConstantNode(parentNode, new Constant(Double.parseDouble(word)));
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
}


