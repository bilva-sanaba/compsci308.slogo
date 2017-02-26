package configuration;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

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
	
	private LinkedHashMap<Object, Class<?>> args; // Hashmap is linked to preserve insertion order
	private List<Object> argOrder;
	
	/**
	 * Constructs a new argument list
	 * 
	 * The command is also stored, so that ID
	 * can be retrieved on invalid parameter error.
	 */
	public Arguments(){
		args = new LinkedHashMap<Object, Class<?>>();
		argOrder = new ArrayList<Object>();
	}
	
	/**
	 * Adds argument to end of list
	 */
	public <T> void add(T arg){
		Object o = (Object)arg;
		argOrder.add(o);
		args.put(o, arg.getClass());
	}
	
	/**
	 * Gets argument Object from list
	 */
	public Object getObject(int index){
		Object returnObject = args.get(index);
		return args.get(returnObject).getClass().cast(returnObject);
	}
	
	/**
	 * Get's class of Object from list
	 * 
	 * I'm unsure how to infer a generic type dynamically without specifying 
	 * the type of object in the client. To get around this right now, I give 
	 * the client the option to obtain the object's previous class and cast
	 * object itself.
	 */
	public Class<?> getClass(Object o){
		return args.get(o);
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
	 * @throws CommandException 
	 */
	public boolean hasSameFormat(Arguments input){
		if(input.numArgs() != numArgs()) return false;
		
		for(int i = 0; i < numArgs(); i++){
			if(!getClass(getObject(i)).equals(input.getClass(input.getObject(i)))) return false;
		}
		return true;
	}
}
