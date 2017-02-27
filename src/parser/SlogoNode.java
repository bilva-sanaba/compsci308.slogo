package parser;

import java.util.List;
/**
 * 
 * @author jwei528
 *
 */
public interface SlogoNode {

	/**
	 * adds a node to the tree
	 * @param child
	 */
	public void addChild(SlogoNode child);
	
	/**
	 * returns the specific String associated with node 
	 * (e.g. fd, 50, repeat)
	 * 
	 */
	public String getWord();
	
	/**
	 * returns the type of String
	 * (e.g. command, param)
	 * 
	 */
	public String getType();
	
	/**
	 * returns the children of a given node
	 * (its parameters)
	 * 
	 */
	public List<SlogoNode> getChildren();
}
