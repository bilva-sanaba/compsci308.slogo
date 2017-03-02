package model;

import configuration.Trajectory;
import configuration.TurtleState;
import model.commands.CommandException;
import model.commands.CommandFactory;
import parser.SlogoParser;
import parser.TokenNode;

public class SlogoModel implements Model {
	private SlogoParser parser;
	
	private CommandFactory defaultCommands;
	private VariableContainer globalVariables;
	
	private Trajectory turtleTrajectory;
	
	public SlogoModel() throws CommandException{
		defaultCommands = new CommandFactory();
		globalVariables = new VariableContainer();
		parser = new SlogoParser();
		
		turtleTrajectory = new Trajectory();
		turtleTrajectory.addLast(new TurtleState());
	}
	
	@Override
	public Trajectory getTrajectory(String commands) throws CommandException {
		Interpreter i = new Interpreter();
		Scope scope = new Scope(defaultCommands, globalVariables, turtleTrajectory, new Scope(true, true, true));

		TokenNode root = parser.parse(new TokenNode(null, new TList()), commands);
		
		for(TokenNode cmd: root.getChildren()){
			i.evaluateTree(cmd, scope);
		}
		
		System.out.println(turtleTrajectory);
		return turtleTrajectory;
	}

	@Override
	public void setLanguage(String language) {
		parser.setLanguage(language);		
	}

}
