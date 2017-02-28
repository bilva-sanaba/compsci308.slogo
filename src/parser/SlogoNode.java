package parser;

import java.util.ArrayList;
import java.util.List;

import model.Arguments;
import model.Token;
import model.TokenType;
/**
 * 
 * @author jwei528
 *
 */
public interface SlogoNode extends Token{

	/**
	 * adds a node to the tree
	 * @param child
	 */
	public void addChild(Token arg);
	
	/**
	 * adds list to node
	 */
	public void addList(ArrayList<String> params);
	
	/**
	 * Returns the token
	 * (e.g. command, param)
	 * 
	 */
	public Token getToken();
	
	/**
	 * returns the children of a given node
	 * (its parameters)
	 * 
	 */
	public Arguments getChildren();
}

