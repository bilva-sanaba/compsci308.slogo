package configuration;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;

import model.Token;
import model.TokenType;
import model.commands.CommandException;

/**
 * This class is used to hold arguments (other commands) to pass to commands
 * 
 * Arguments will all be commands so it is easy to retrieve and store information
 * without having to cast on extraction.
 *
 * @author DhruvKPatel
 */
public class Arguments {
	
	private List<Token> arguments;
	
	/**
	 * Constructs a new argument list
	 * 
	 * The command is also stored, so that ID
	 * can be retrieved on invalid parameter error.
	 */
	public Arguments(Token[] args){
		arguments = new ArrayList<Token>(Arrays.asList(args));
	}
	
	public Arguments(){
		this(null);
	}
	
	/**
	 * Adds argument to end of list
	 */
	public void add(Token arg){
		arguments.add(arg);
	}
	
	/**
	 * Gets argument from list index
	 * @return 
	 */
	public Token get(int index){
		Token t = arguments.get(index); // This is two lines to prepare for extension
		return t;
	}
	
	/**
	 * Get number of arguments
	 */
	public int numArgs(){
		return arguments.size();
	}
	
	/**
	 * Compares argument types of two sets of arguments
	 * 
	 * To return true, set must have same length, and all
	 * corresponding arguments must have same type.
	 */
	public boolean hasSameFormat(Arguments input){
		if(input.numArgs() != numArgs()) return false;
		
		for(int i = 0; i < numArgs(); i++){
			if(!this.get(i).getType().equals(input.get(i).getType())) return false;
		}
		return true;
	}
}
