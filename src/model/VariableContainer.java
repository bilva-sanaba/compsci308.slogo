package model;

import java.util.HashSet;
import java.util.Set;

public class VariableContainer {
	private Set<Variable> variables;
	
	public VariableContainer(){
		variables = new HashSet<>();
	}
	
	public VariableContainer(VariableContainer v){
		this();
		variables = new HashSet<Variable>(v.variables);
	}
	
	/**
	 * Searches variables for certain name.
	 * Returns value if variable exists,
	 * null if variable does not exist.
	 */
	public Constant getValue(String name){
		for(Variable v: variables){
			if(v.getName().equals(name)) return v.getValue();
		}
		return null;
	}
	
	/**
	 * Adds variable to container.
	 * If variable already exists, overwrites.
	 * @param v
	 */
	public void addVariable(Variable v){
		variables.add(v);
	}
}
