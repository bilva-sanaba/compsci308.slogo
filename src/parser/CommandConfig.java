package parser;

public class CommandConfig {
	private String command;
	private SlogoNode head;
	
	public CommandConfig(String command, SlogoNode head){
		this.command = command;
		this.head = head;
	}
	
	public String getCommand(){
		return command;
	}
	
	public SlogoNode getHead(){
		return head;
	}
	
}
