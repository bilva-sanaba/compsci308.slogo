package model.tokens;

import java.util.HashMap;
import java.util.Map.Entry;

import model.exceptions.CommandException;

import java.util.Set;

/**
 * Container for Variable Objects
 * 
 * Note: this is not a token. It's a container for variable Tokens.
 * 
 * @author DhruvKPatel
 */
public class VariableContainer {
	private HashMap<String, Constant> variables;
	
	/**
	 * Constructs a new container with no initial variables
	 */
	public VariableContainer(){
		variables = new HashMap<>();
	}

	/**
	 * Copy constructor
	 * @param original
	 */
	public VariableContainer(VariableContainer original){
		variables = new HashMap<>();
		for(Entry<String, Constant> e : original.variables.entrySet()){
			variables.put(e.getKey(), new Constant(e.getValue().getVal()));
		}
	}
	
	/**
	 * Sets the value of a Variable
	 * @param Variable
	 * @param Constant
	 */
	public void set(Variable v, Constant c){
		variables.put(v.getName(), c);
	}
	
	/**
	 * Returns the value of a variable
	 * @param Variable
	 * @return Constant
	 * @throws CommandException
	 */
	public Constant get(Variable v) throws CommandException{
		if(!variables.containsKey(v.getName())) throw new CommandException(String.format("Variable does not exist: %s", v.getName()));
		return variables.get(v.getName());
	}
	
	/**
	 * Checks if the variable is contained in the set
	 * @param v
	 * @return
	 */
	public boolean contains(Variable v){
		return variables.containsKey(v.getName());
	}
	
	/**
	 * Returns a list of all variable names
	 * @return
	 */
	public Set<String> getVariableNames(){
		return variables.keySet();
	}
	
	/**
	 * Returns a string reference for all variable values
	 */
	public String toString(){
		String v = "- - - - - - - - -\nVariables:\n";
		for(Entry<String, Constant> e : variables.entrySet()){
			v += String.format("%s: %f\n", e.getKey(), e.getValue().getVal());
		}
		v += "- - - - - - - - -\n";
		return v;
	}

}
