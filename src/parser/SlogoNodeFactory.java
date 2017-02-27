package parser;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.List;
import java.util.ResourceBundle;

public class SlogoNodeFactory {

	public SlogoNodeFactory(){
		
	}
	
	private static ResourceBundle languageResourceBundle;
	private static ResourceBundle syntaxResourceBundle;
	
	public static final String DEFAULT_RESOURCES_PACKAGE = "resources.languages/";
	
	public static final String LANGUAGE = "English";
	public static final String SYNTAX = "Syntax";
	
	private static List<String> possibleCommands = new ArrayList<String>();
	
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
	
	public static SlogoNode makeSlogoNode(String word){
		createValueList();
		SlogoNode slogoNode;
		if(possibleCommands.contains(word)){//word is in resources
			System.out.println(1);
			slogoNode = new SlogoNode(word, "command");
		}
		else if(word.equals("[")){
			slogoNode = new SlogoNode(null, "group");
		}
		else if(word.equals("]")){
			slogoNode = new SlogoNode(null, "endgroup");
		}
		else{
			slogoNode = new SlogoNode(word, "param");
		}
	
		return slogoNode;
	}
}
