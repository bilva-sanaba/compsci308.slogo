package model.commands.advancedCommands;

import java.util.List;

import model.Arguments;
import model.Constant;
import model.Interpreter;
import model.Scope;
import model.Token;
import model.Variable;
import model.VariableContainer;
import model.commands.AbstractCommand;
import model.commands.CommandException;
import parser.TokenNode;

public class TemplateCommand extends AbstractCommand {
	
	private Arguments defaultArgs;
	private List<TokenNode> subCommands;
	private List<Variable> argumentVariables;
	private String id;
	
	public TemplateCommand(String id){
		defaultArgs = new Arguments();
		this.id = id;
	}
	
	@Override
	public double execute(Arguments args) throws CommandException {
		Interpreter subInterpreter = new Interpreter();
		
		if(subCommands == null) throw new CommandException(String.format("No commands defined in list: %s", getID()));
		
		VariableContainer myVariables = getScope().getVariables();
		
		for(int i = 0; i < argumentVariables.size(); i++){
			myVariables.addVariable(new Variable(argumentVariables.get(i).getName(), ((Variable)args.get(i)).getValue()));
		}
		
		Constant answer = new Constant(0);
		for(TokenNode subCommand: subCommands){
			answer = (Constant)subInterpreter.evaluateTree(subCommand, getScope());
		}
		return answer.getVal();
	}

	@Override
	public Arguments getDefaultArgs() {
		return defaultArgs;
	}
	
	public void setDefaultArgs(Arguments defaultArgs){
		this.defaultArgs = defaultArgs;
	}

	@Override
	public String getID() {
		return this.id;
	}

	@Override
	public Scope getScopeRequest() {
		return new Scope(true, true, true);
	}

	public void setOnEvaluation(List<TokenNode> children) {
		this.subCommands = children;		
	}

	public void setOrderedVariableArguments(Arguments variables) {
		for(Token t: variables){
			argumentVariables.add(new Variable(((Variable)t).getName()));
		}
	}
}
