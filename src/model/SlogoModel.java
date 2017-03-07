package model;

import java.util.ArrayList;
import java.util.Arrays;

import configuration.Trajectory;
import configuration.TurtleState;
import model.commands.CommandException;
import model.commands.CommandFactory;
import parser.SlogoParser;
import parser.TokenNode;

/**
 * Main class for Model implementation
 * 
 * @author Dhruv Patel
 * @author Jacob Weiss
 */
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
//		//EDIT
//		ArrayList<String> commandList = fillList(commands);
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
	
	private ArrayList<String> fillList(String command){
		command=command.trim();
		return new ArrayList<String>(Arrays.asList(command.split(" ")));
	}

}
