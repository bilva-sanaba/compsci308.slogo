package model;

import model.commands.CommandFactory;
import model.configuration.Scope;
import model.configuration.Trajectory;
import model.exceptions.CommandException;
import model.tokens.VariableContainer;
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
	private World world;
	
	public SlogoModel() throws CommandException{
		globalCommands = new CommandFactory();
		globalVariables = new VariableContainer();
		turtleTrajectory = new Trajectory();
		world = new World(turtleTrajectory, globalVariables, globalCommands);
		
		parser = new SlogoParser(globalCommands);		
	}
	

	/**
	 * Evaluates a String of commands and modifies world of turtles/background to account for this
	 * 
	 * Returns modified world.
	 */
	@Override
	public UnmodifiableWorld getWorld(String commands) throws CommandException {

		Interpreter i = new Interpreter();
		Scope scope = new Scope(globalCommands, globalVariables, turtleTrajectory, world, new Scope(true, true, true, true));
		TokenNode root = parser.parse(commands);

		for(TokenNode cmd: root.getChildren()){
			i.evaluateTree(cmd, scope); // for multiple turtles
		}
		
//		System.out.println(world); // Uncomment this to test functionality of model
		return (UnmodifiableWorld) world;
	}
	
	// This method should not be used after front-end implements multiple turtles.

	public Trajectory getTrajectory(String commands) throws CommandException{
		Interpreter i = new Interpreter();
		Scope scope = new Scope(globalCommands, globalVariables, turtleTrajectory, world, new Scope(true, true, true, true));	
		TokenNode root = parser.parse(commands);
		
		for(TokenNode cmd: root.getChildren()){
			i.evaluateTree(cmd, scope);
		}
		
		System.out.println(world); // Uncomment this to test functionality of model
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