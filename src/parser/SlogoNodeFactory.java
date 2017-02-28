package parser;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.List;
import java.util.ResourceBundle;

import model.Constant;
import model.Token;
import model.Variable;
import model.commands.CommandException;
import model.commands.CommandFactory;

public class SlogoNodeFactory {
	

	private static ResourceBundle languageResourceBundle;
	private static ResourceBundle syntaxResourceBundle;
	
	public static final String DEFAULT_RESOURCES_PACKAGE = "resources.languages/";
	
	public static final String LANGUAGE = "English";
	public static final String SYNTAX = "Syntax";
	
	private static List<String> possibleCommands = new ArrayList<String>();
	
	public SlogoNodeFactory(){
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
			for(String v: valueList){
				possibleCommands.add(v);
			}
		}
	}
	

	public SlogoNode genSlogoNode(String word) throws CommandException{
		createValueList();
		SlogoNode slogoNode;
		if(possibleCommands.contains(word)){//word is in resources
			CommandFactory cFactory = new CommandFactory();
			Token t = cFactory.getCommand(word);
			slogoNode = new TokenNode(t);
		}
		else if(word.equals("[")){
			slogoNode = new TokenNode(null);
		}
		else if(word.equals("]")){
			slogoNode = new TokenNode(null);
		}
		else if(Double.valueOf(word)!=null){
			slogoNode = new TokenNode(new Constant(Double.parseDouble(word)));
		}
		else{
			slogoNode = new TokenNode(new Variable(word));
		}
		return slogoNode;
	}
}


