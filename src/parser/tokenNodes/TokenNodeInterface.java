package parser.tokenNodes;

import java.util.List;

import model.Token;

/**
 * Interface for TokenNode
 * TokenNode object consists of its parent, List of children and current Token
 * @author Jacob Weiss
 *
 */

public interface TokenNodeInterface {
	
	/**
	 * Adds a TokenNode to the tree
	 * @param child
	 */
	public void addChild(TokenNode child);
	
	/**
	 * Adds a TokenNode to the tree if only the Token is given
	 * @param childVal
	 */
	public void addChild(Token childVal);
	
	/**
	 * Gets the Token from the TokenNode
	 */
	public Token getToken();
	
	/**
	 * Gets the children of the TokenNode in the tree
	 * @return
	 */
	public List<TokenNode> getChildren();
	
	/**
	 * Gets the parent of the TokenNode in the tree
	 * @return
	 */
	public TokenNodeInterface getParent();

}
