
package model.commands.advancedCommands;

import java.util.ArrayList;
import java.util.List;

import model.commands.AbstractCommand;
import model.configuration.Arguments;
import model.configuration.Scope;
import model.exceptions.CommandException;
import model.tokens.Constant;
import model.tokens.TList;
import model.tokens.Variable;
import model.tokens.VariableContainer;

/**
 * This is a Template Command for all Commands Defined by the user.
 * @author DhruvKPatel
 *
 */
public class TemplateCommand extends AbstractCommand {
	
	private Arguments defaultArgs;
	private TList subCommands;
	private List<Variable> argumentVariables;
	private String id;
	
	public TemplateCommand(String id){
		defaultArgs = new Arguments();
		this.id = id;
	}
	
	@Override
	public double execute(Arguments args, Scope scope) throws CommandException {
		if(subCommands == null) throw new CommandException(String.format("No commands defined in list: %s", getID()));
		
		VariableContainer subVariables = new VariableContainer(scope.getVariables()); // COPIES scope variable, so that instance variables are not added to global set.
		
		System.out.println("b4:" + subVariables);
		for(int i = 0; i < argumentVariables.size(); i++){	// Adds argument variables to sub-scope
			subVariables.set(argumentVariables.get(i), args.getConstant(i));
		}
		System.out.println("after: " + subVariables);
				
		Scope subScope = new Scope(scope.getCommands(), subVariables, scope.getTrajectory(), scope.getWorld(), getScopeRequest());
		
		Arguments answer = subCommands.evaluateContents(subScope);
		return(answer.getDouble(answer.numArgs() - 1));
		
	}

	@Override
	public Arguments getDefaultArgs() {
		return defaultArgs;
	}

	@Override
	public String getID() {
		return this.id;
	}

	@Override
	public Scope getScopeRequest() {
		return new Scope(true, true, true, true);
	}

	/**
	 * When command is evaluated, this TList's children will be evaluated
	 * @param subCommands
	 */
	public void setOnEvaluation(TList subCommands) {
		this.subCommands = subCommands;		
	}

	/**
	 * Sets expected variable names of command given Arguments of variable names.
	 * Also infers default arguments as Constants with values of current variables
	 * @param variables
	 * @throws CommandException 
	 */
	public void setOrderedVariableArguments(Arguments variables) throws CommandException {
		defaultArgs = new Arguments();
		argumentVariables = new ArrayList<>();
		
		for(int i = 0; i < variables.numArgs(); i++){
			argumentVariables.add(variables.getVariable(i));
			defaultArgs.add(new Constant(0));	// Default variable
		}
	}
}