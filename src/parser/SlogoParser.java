package parser;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.List;
import java.util.ResourceBundle;

import model.Arguments;
import model.Constant;
import model.Token;
import model.commands.CommandException;



/**
 * The parser for user SLogo commands. We read the command and convert it into a tree
 * @author jwei528
 *
 */

public class SlogoParser {
	private static ArrayList<SlogoNode> nodeList = new ArrayList<SlogoNode>();
	
	public SlogoParser(){
	}
	
	public static SlogoNode parse(String command) throws CommandException{
		
		ArrayList<String> commandList = fillList(command);
		
		SlogoNode root = new TokenNode(null);
		SlogoNode head=root;
	
		
		for(String word: commandList){
			SlogoNode slogoNode;
			SlogoNodeFactory factory = new SlogoNodeFactory();
			slogoNode = factory.genSlogoNode(word);
			if(word.equals("[")){
				slogoNode = parse(command.substring(command.indexOf("[")+1));
			}
			else if(word.equals("]")){
				//evaluate from head
				//slogoNode = parse(command.substring(command.indexOf("]")+1));
				break;
			}
			
			root.addChild(slogoNode);
			if(slogoNode.getToken().getType().equals("COMMAND")){ 
				root=slogoNode;
			}
			
		}
		return head;
	}

	private static ArrayList<String> fillList(String command){
		return new ArrayList<String>(Arrays.asList(command.split(" ")));
	}
	
}

