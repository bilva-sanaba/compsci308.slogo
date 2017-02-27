package parser;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.List;
import java.util.ResourceBundle;



/**
 * The parser for user SLogo commands. We read the command and convert it into a tree
 * @author jwei528
 *
 */

public class SlogoParser {
	private static ArrayList<SlogoNode> nodeList = new ArrayList<SlogoNode>();
	private static SlogoNode parentNode;

	private static SlogoNode currNode;
	private static String command = "repeat 9 [ repeat 180 [ fd 3 rt 2 ] rt 40 ]";
	private static ArrayList<String> commandList = new ArrayList<String>();
	
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

	
	public static void parse(SlogoNode head, ArrayList<String> commandList){
		
		createValueList();
		SlogoNode root = new GroupNode(null);

		head=root;
		nodeList.add(head);
	
		
		for(String word: commandList){

			/*SlogoNodeFactory factory = new SlogoNodeFactory();
			SlogoNode slogoNode = factory.genSlogoNode(word);*/
			//commandQueue.poll();
			SlogoNode slogoNode;
			if(possibleCommands.contains(word)){//word is in resources
				slogoNode = new CommandNode(word);
			}
			else if(word.equals("[")){
				slogoNode = new GroupNode(word);
				currNode=parentNode;
				ArrayList<String> newCommandList = new ArrayList<String>();
				for(String w: commandList){
					newCommandList.add(w);
				}
				newCommandList.remove(word); //MUST EDIT TO REMOVE ALL HIT WORDS
				//String newCommand = join(commandList);
				parse(slogoNode, newCommandList);
			}
			else if(word.equals("]")){
				slogoNode = new EndGroupNode(word);
				ArrayList<String> newCommandList = new ArrayList<String>();
				for(String w: commandList){
					newCommandList.add(w);
				}
				newCommandList.remove(word);
				//String newCommand = join(commandList);
				parse(currNode, newCommandList);
			}
			else{
				slogoNode = new ParamNode(word);
			}
			
			root.addChild(slogoNode);
			if(!slogoNode.getType().equals("param")){
				parentNode=root;
				root=slogoNode;
			}
			
		
		}
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
	
	private static void fillList(String command){
		commandList = new ArrayList<String>(Arrays.asList(command.split(" ")));
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



