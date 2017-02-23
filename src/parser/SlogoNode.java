package parser;

import java.util.ArrayList;
import java.util.List;

public class SlogoNode {
	private List<SlogoNode> children = null;
	private String word;
	private String type;
	
	public SlogoNode(String word, String type){
		this.children = new ArrayList<>();
		this.word=word;
		this.type=type;
	}
	
	public void addChild(SlogoNode child){
		children.add(child);
	}
	
	public String getWord(){
		return word;
	}
	
	public String getType(){
		return type;
	}
	
	public List<SlogoNode> getChildren(){
		return children;
	}
}
