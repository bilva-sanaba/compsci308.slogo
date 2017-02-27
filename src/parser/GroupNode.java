package parser;

/**
 * Tree node
 * @author jwei528
 */
import java.util.ArrayList;
import java.util.List;

public class GroupNode implements SlogoNode {
	private List<SlogoNode> children = null;
	private String word;
	private String type;
	
	public GroupNode(String word){
		this.children = new ArrayList<>();
		this.word=word;
		this.type="group";
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