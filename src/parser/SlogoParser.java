package parser;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.List;
import java.util.ResourceBundle;

import configuration.Arguments;
import model.Token;



/**
 * The parser for user SLogo commands. We read the command and convert it into a tree
 * @author jwei528
 *
 */

public class SlogoParser {
	private static ArrayList<SlogoNode> nodeList = new ArrayList<SlogoNode>();
	
	private static SlogoNode head = new TokenNode(null);
	private static SlogoNode parentNode;
	private static SlogoNode currNode;
	private static String command = "repeat 9 [ repeat 180 [ fd 3 rt 2 ] rt 40 ]";
	//private static ArrayList<String> commandList = new ArrayList<String>();
	//private static ArrayList<Token> tokenList = new ArrayList<Token>();
	
	private static ResourceBundle languageResourceBundle;
	private static ResourceBundle syntaxResourceBundle;
	
	public static final String DEFAULT_RESOURCES_PACKAGE = "resources.languages/";
	
	public static final String LANGUAGE = "English";
	public static final String SYNTAX = "Syntax";
	
	private static List<String> possibleCommands = new ArrayList<String>();


	
	public SlogoParser(){
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

	
	public static SlogoNode parse(String command){
		
		createValueList();
		ArrayList<String> commandList = fillList(command);
		Arguments tokenList = makeTokenList(commandList);
		SlogoNode root = new TokenNode(null);
		//root.addChild(tokenList); //update
	
		
		for(String word: commandList){
			SlogoNode slogoNode;
			SlogoNodeFactory factory = new SlogoNodeFactory();
			slogoNode = factory.genSlogoNode(word);
			if(word.equals("[")){
				//currNode=parentNode;
				parse(command.substring(command.indexOf("[")+1));
			}
			else if(word.equals("]")){
				//evaluate up until null node
				slogoNode = parse(command.substring(command.indexOf("]")+1));
			}
			
			root.addChild(slogoNode);
			if(slogoNode is a command){
				parentNode=root;
				root=slogoNode;
			}
			
		}
		return head;
	}
	
	/*private static void traverse(SlogoNode slogoNode){
		if(slogoNode.getType().equals("command")){
			//create new command object
			System.out.println(1);
			for(SlogoNode sn: slogoNode.getChildren()){
				traverse(sn);
			SlogoNode slogoNode = SlogoNodeFactory.makeSlogoNode(word);
			
			if(slogoNode.getType().equals("endgroup")){
				root=parentNode;

			}
		}
		else if(slogoNode.getType().equals("head")){
			for(SlogoNode sn: slogoNode.getChildren()){
				traverse(sn);
			}
		}
		else if(slogoNode.getType().equals("param")){
			System.out.println(0);
			//create parameter object
		}
		else if(slogoNode.getType().equals("group")){
			System.out.println(2);
			for(SlogoNode sn: slogoNode.getChildren()){
				traverse(sn);
			}	
		}
		else if(slogoNode.getType().equals("endgroup")){
			System.out.println(3);
			for(SlogoNode sn: slogoNode.getChildren()){
				traverse(sn);
			}
		}
	}*/
	
	/*private static String join(ArrayList<String> commandList){
		String result="";
		for(String c: commandList){
			result=result + c + " ";
		}
		return result;
	}*/
	
	private static ArrayList<String> fillList(String command){
		return new ArrayList<String>(Arrays.asList(command.split(" ")));
	}
	
	private static Arguments makeTokenList(ArrayList<String> commandList){
		SlogoNodeFactory factory = new SlogoNodeFactory();
		Arguments tokenList = new Arguments();
		for(String c: commandList){
			tokenList.add(factory.genSlogoNode(c));
		}
		return tokenList;
	}

	/*public static void main(String[] args){
		SlogoNode head = new SlogoNode(null, "head");
		fillList(command);
		parse(head, commandList);
		int count = 0;
		for(SlogoNode s: nodeList){
			count++;
		}
		System.out.println(count);
	}*/

	
}

