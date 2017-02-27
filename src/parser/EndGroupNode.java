package parser;

/**
 * Tree node
 * @author jwei528
 */
import java.util.ArrayList;
import java.util.List;

public class EndGroupNode implements SlogoNode {
	private List<SlogoNode> children = null;
	private String word;
	private String type;
	
	public EndGroupNode(String word){
		this.children = new ArrayList<>();
		this.word=word;
		this.type="endgroup";
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
