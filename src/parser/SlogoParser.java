package parser;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.ResourceBundle;

import org.w3c.dom.Node;

/**
 * The parser for user SLogo commands. We read the command and convert it into a tree
 * @author jwei528
 *
 */

public class SlogoParser {
	private static SlogoNode head;
	private static SlogoNode parentNode;
	private static String command = "repeat 9 [ repeat 180 [ fd 3 rt 2] rt 40";
	private static ResourceBundle languageResourceBundle;
	private static ResourceBundle syntaxResourceBundle;
	
	public static final String DEFAULT_RESOURCES_PACKAGE = "resources.languages/";
	
	public static final String LANGUAGE = "English";
	public static final String SYNTAX = "Syntax";
	
	private static List<String> possibleCommands = new ArrayList<String>();
	
	public SlogoParser(){
	}
	
	private void createValueList(){
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
	
	private SlogoNode createTree(String command){
		
		SlogoNode root = new SlogoNode(null, "group");
		head=root;
	
		ArrayList<String> commandList = new ArrayList<String>(Arrays.asList(command.split(" ")));
	
		for(String word: commandList){
			SlogoNode slogoNode;
			if(possibleCommands.contains(word)){//word is in resources
				System.out.println(1);
				slogoNode = new SlogoNode(word, "command");
			}
			else if(word.equals(syntaxResourceBundle.getString("ListStart"))){
				slogoNode = new SlogoNode(null, "group");
			}
			else if(word.equals(syntaxResourceBundle.getString("ListEnd"))){
				slogoNode = new SlogoNode(null, "endgroup");
			}
			else{
				slogoNode = new SlogoNode(word, "param");
			}
		
			if(slogoNode.getType().equals("endgroup")){
				root=parentNode;
			}
			else{
				root.addChild(slogoNode);
			}
		
			if(slogoNode.getType().equals("command") || slogoNode.getType().equals("group")){
				parentNode=root;
				root=slogoNode;
			}
		}
		
		return head;
	}
	
	public CommandConfig parse(String command){
		createValueList();
		return new CommandConfig(command, createTree(command));
	}
}



