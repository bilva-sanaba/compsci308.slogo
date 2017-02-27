package parser;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.List;
import java.util.ResourceBundle;

import model.Token;
import model.Variable;

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
	

	public SlogoNode genSlogoNode(String word){
		createValueList();
		SlogoNode slogoNode;
		if(possibleCommands.contains(word)){//word is in resources
			//create command node
		}
		else if(word.equals("[")){
			slogoNode = new GroupNode(word);
		}
		else if(word.equals("]")){
			slogoNode = new EndGroupNode(word);
		}
		else{
			slogoNode = new TokenNode(new Variable(word));
		}
		return slogoNode;
	}
}


