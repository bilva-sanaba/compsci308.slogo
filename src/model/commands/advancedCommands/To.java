package model.commands.advancedCommands;

import model.Arguments;
import model.Scope;
import model.TList;
import model.Token;
import model.TokenType;
import model.commands.AbstractCommand;
import model.commands.CommandException;
import parser.tokenNodes.TokenNode;

/**
 * Class for "TO" command
 * @author DhruvKPatel
 *
 */
public class To extends AbstractCommand {
	private String commandName;

	@Override
	public double execute(Arguments args, Scope scope) throws CommandException {
		commandName = args.getCommand(0).getID(); 	 // Command Name
		TList parameters = args.getTList(1);		 // Variable Names
		TList contents = args.getTList(2);			 // Full commands with variables

		TemplateCommand newCommand = new TemplateCommand(commandName);
		newCommand.setOnEvaluation(contents);
		newCommand.setOrderedVariableArguments(getArgumentsFromList(parameters));
		
		scope.getCommands().registerCommand(commandName, newCommand);
		return 1;
	}

	/**
	 * Converts a list of Variables to an "Arguments" of Variables.
	 * 
	 * Since all arguments MUST be variables, it will throw an error if they are not.
	 * @param list
	 * @return
	 * @throws CommandException
	 */
	private Arguments getArgumentsFromList(TList list) throws CommandException{
		Arguments args = new Arguments();
		for(TokenNode t : list.getChildren()){
			Token var = t.getToken();
			if(var.getType() != TokenType.VARIABLE) throw new CommandException(String.format("Cannot define command \"%s\". Second argument contain only variables", commandName));
			else args.add(var);
		}
		return args;
	}
	
	
//	private Arguments getDefaultVariables(Arguments variables) { // This code serves no purpose
//		Arguments defaultVariables = new Arguments();
//		for(int i = 0; i < variables.numArgs(); i++){
//			Variable v = variables.getVariable(i);
//			defaultVariables.add(v);
//		}
//		return defaultVariables;
//	}

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
