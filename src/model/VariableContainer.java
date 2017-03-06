package model;

import java.util.HashMap;

import model.commands.CommandException;

public class VariableContainer {
	private HashMap<String, Constant> variables;
	
	public VariableContainer(){
		variables = new HashMap<>();
	}
	
	public VariableContainer(VariableContainer original){
		variables = new HashMap<>(original.variables);
	}
	
	public void set(Variable v, Constant c){
		variables.put(v.getName(), c);
		System.out.println(variables.size());
	}
	
	public Constant get(Variable v) throws CommandException{
		if(!variables.containsKey(v.getName())) throw new CommandException(String.format("Variable does not exist: %s", v.getName()));
		return variables.get(v.getName());
	}
	
	public boolean contains(Variable v){
		return variables.containsKey(v.getName());
	}
}
