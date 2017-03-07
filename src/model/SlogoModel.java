package model;

import configuration.Trajectory;
import configuration.TurtleState;
import model.commands.CommandException;
import model.commands.CommandFactory;
import parser.SlogoParser;
import parser.tokenNodes.TokenNode;

/**
 * Main class for Model implementation
 * 
 * @author Dhruv Patel
 * @author Jacob Weiss
 */
public class SlogoModel implements Model {
	private SlogoParser parser;
	
	private CommandFactory globalCommands;
	private VariableContainer globalVariables;
	
	private Trajectory turtleTrajectory;
	
	public SlogoModel() throws CommandException{
		globalCommands = new CommandFactory();
		globalVariables = new VariableContainer();
		parser = new SlogoParser(globalCommands);
		
		turtleTrajectory = new Trajectory();
		turtleTrajectory.addLast(new TurtleState());
	}
	
	@Override
	public Trajectory getTrajectory(String commands) throws CommandException{
		Interpreter i = new Interpreter();
		Scope scope = new Scope(globalCommands, globalVariables, turtleTrajectory, new Scope(true, true, true));
		
		TokenNode root = parser.parse(new TokenNode(null, new TList()), commands);
		
		for(TokenNode cmd: root.getChildren()){
			i.evaluateTree(cmd, scope);
		}
		
		return turtleTrajectory.getMostRecentAdditions();
	}

	@Override
	public void setLanguage(String language) {
		parser.setLanguage(language);		
	}

	@Override
	public VariableContainer getGlobalVariables() {
		return globalVariables;
	}
	
	@Override
	public CommandFactory getGlobalCommands() {
		return globalCommands;
	}
}
