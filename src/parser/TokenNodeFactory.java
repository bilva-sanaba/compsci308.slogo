package parser;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

import model.Constant;
import model.Token;
import model.Variable;
import model.commands.CommandException;
import model.commands.CommandFactory;
/**
 * 
 * @author Jacob Weiss
 *
 */
public class TokenNodeFactory {
	

	private static ResourceBundle languageResourceBundle;
	private static ResourceBundle syntaxResourceBundle;
	
	public static final String DEFAULT_RESOURCES_PACKAGE = "resources.languages/";
	
	public static final String LANGUAGE = "English";
	public static final String SYNTAX = "Syntax";
	
	private static List<String> possibleCommands = new ArrayList<String>();
	private static Map<String, ArrayList<String>> keyMap = new HashMap<String, ArrayList<String>>();
	
	public TokenNodeFactory(){
	}
	
	private static void createValueList(){
		//may need try and catch
		syntaxResourceBundle = ResourceBundle.getBundle(DEFAULT_RESOURCES_PACKAGE + SYNTAX);
		languageResourceBundle = ResourceBundle.getBundle(DEFAULT_RESOURCES_PACKAGE + LANGUAGE);
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
		createValueList();
		TokenNode tokenNode = new TokenNode(parentNode, null);
		if(possibleCommands.contains(word)){//word is in resources
			String wordID = findWordID(word);
			CommandFactory cFactory = new CommandFactory();
			Token t = cFactory.getCommand(wordID);
			tokenNode = new TokenNode(parentNode, t);
		}
		else if(Double.valueOf(word)!=null){
			tokenNode = new TokenNode(parentNode, new Constant(Double.parseDouble(word)));
		}
		else if(word.substring(0,1)==":"){ //include : check
			tokenNode = new TokenNode(parentNode, new Variable(word));
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
}


