package model.commands.advancedCommands;

import java.util.ArrayList;
import java.util.List;

import model.Arguments;
import model.Command;
import model.Constant;
import model.Interpreter;
import model.Scope;
import model.TList;
import model.TokenType;
import model.Variable;
import model.commands.AbstractCommand;
import model.commands.CommandException;
import parser.TokenNode;

/**
 * Class for "TO" command
 * @author DhruvKPatel
 *
 */
public class To extends AbstractCommand {
	private String commandName;
	
	private Arguments variables = new Arguments();
	private Arguments variablesValues = new Arguments();

	@Override
	public double execute(Arguments args) throws CommandException {
		commandName = ((Command)args.get(0)).getID(); // Command Name
		TList parameters = (TList)args.get(1); // Variable Names
		TList contents = (TList)args.get(2); // Full commands with variables

		TemplateCommand newCommand = new TemplateCommand(commandName);
		newCommand.setOnEvaluation(contents.getChildren());
		
		parseVariablesList(parameters.getChildren());
		
		newCommand.setDefaultArgs(variablesValues);
		newCommand.setOrderedVariableArguments(variables);
		newCommand.setScope(new Scope(getScope().getCommands(), getScope().getVariables(), getScope().getTrajectory(), newCommand.getScopeRequest()));

		return 1;
	}

	private void parseVariablesList(List<TokenNode> children) throws CommandException {
		for(TokenNode node: children){
			if(node.getToken().getType() != TokenType.VARIABLE) throw new CommandException(String.format("Cannot define command \"%s\". Second argument contain only variables", commandName));
			else{
				Variable v = ((Variable)node.getToken());
				variables.add(v);
				variablesValues.add(v);
			}
		}
	}

	/**
 	 * Expected Arguments:
	 * 1: Command (Template)
	 * 2: List 
	 * 3: List
	 */
	@Override
	public Arguments getDefaultArgs() {
		Arguments args = new Arguments();
		args.add(new TemplateCommand("fake"));
		args.add(new TList());
		args.add(new TList());
		return args;
	}

	@Override
	public String getID() {
		return "To";
	}

	@Override
	public Scope getScopeRequest() {
		return new Scope(true, true, true);
	}

}
