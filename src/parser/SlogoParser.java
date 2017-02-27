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
	private static SlogoNode head;
	private static SlogoNode parentNode;
	//private static String command = "repeat 9 [ repeat 180 [ fd 3 rt 2 ] rt 40 ]";
	
	public SlogoParser(){
	}
	
	
	public static SlogoNode parse(String command){
		
		SlogoNode root = new SlogoNode(null, "head");
		head=root;
	
		ArrayList<String> commandList = new ArrayList<String>(Arrays.asList(command.split(" ")));
	
		for(String word: commandList){
			SlogoNode slogoNode = SlogoNodeFactory.makeSlogoNode(word);
			
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
	
	private void traverse(SlogoNode head){
		if(head.getWord().equals("command")){
			for(SlogoNode sn: head.getChildren()){
				traverse(sn);
			}
		}
		else if(head.getWord().equals("param")){
			//evaluate
		}
	}
	
}



