package configuration;

import java.util.ArrayList;
import java.util.List;

/**
 * This class is used to hold arguments to pass to commands
 *
 * @author DhruvKPatel
 */
public class Arguments {
	
	List<Object> args;
	
	/**
	 * Constructs a new argument list
	 */
	public Arguments(){
		args = new ArrayList<Object>();
	}
	
	/**
	 * Adds argument to end of list
	 */
	public void add(Object arg){
		args.add(arg);
	}
	
	/**
	 * Gets argument from list
	 */
	public Object get(int index){
		return args.get(index);
	}
	
	/**
	 * Get number of arguments
	 */
	public int numArgs(){
		return args.size();
	}
	
	/**
	 * Compares argument types of two sets of arguments
	 * 
	 * To return true, set must have same length, and all
	 * corresponding arguments must have same type.
	 */
	public boolean hasSameFormat(Arguments input){
		if(input.numArgs() != numArgs()) return false;
		
		for(int i = 0; i < input.numArgs(); i++)
			if(!get(i).getClass().equals(input.get(i).getClass())) return false;
		
		return true;		
	}
}
