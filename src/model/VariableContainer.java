package model;

import java.util.HashMap;

public class VariableContainer {
	private HashMap<String, Constant> variables;
	
	public VariableContainer(){
		variables = new HashMap<>();
	}
	
	public void set(Variable v, Constant c){
		variables.put(v.getName(), c);
		System.out.println(variables.size());
	}
	
	public Constant get(Variable v){
		if(!variables.containsKey(v.getName())) return new Constant(0);
		return variables.get(v.getName());
	}
	
	public boolean contains(Variable v){
		return variables.containsKey(v.getName());
	}
}
