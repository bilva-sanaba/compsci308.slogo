package model;

import java.util.HashMap;
import java.util.Set;

import model.commands.CommandException;

/**
 * Container for Variable Objects
 * 
 * @author DhruvKPatel
 *
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
		variables = new HashMap<>(original.variables);
	}
	
	/**
	 * Sets the value of a Variable
	 * @param Variable
	 * @param Constant
	 */
	public void set(Variable v, Constant c){
		variables.put(v.getName(), c);
		System.out.println(variables.size());
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

}
