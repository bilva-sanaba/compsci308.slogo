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
	 * returns the type of String
	 * (e.g. command, param)
	 * 
	 */
	public String getToken();
	
	/**
	 * returns the children of a given node
	 * (its parameters)
	 * 
	 */
	public List<SlogoNode> getChildren();
}
