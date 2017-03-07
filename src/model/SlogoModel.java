package model;

import configuration.Trajectory;
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
	private World world;
	
	public SlogoModel() throws CommandException{
		globalCommands = new CommandFactory();
		globalVariables = new VariableContainer();
		parser = new SlogoParser(globalCommands);
		
		world = new World();
		
//		turtleTrajectory = new Trajectory();
//		turtleTrajectory.addLast(new TurtleState());
	}
	
	@Override
	public Trajectory getTrajectory(String commands) throws CommandException{
		Interpreter i = new Interpreter(world);
		
		Scope scope = new Scope(globalCommands, globalVariables, new Trajectory(), world, new Scope(true, true, true, true));
		
		TokenNode root = parser.parse(new TokenNode(null, new TList()), commands);
		
		for(TokenNode cmd: root.getChildren()){
//			i.evaluateTree(cmd, scope); // for single turtle only
			i.evaluateForTurtles(world.getActiveTurtles(), cmd, scope); // for multiple turtles
		}
		
//		System.out.println(world); // Uncomment this to test functionality of model
		Trajectory t =  world.getTurtle(0).getTrajectory().getMostRecentAdditions(); // this is temporary. 
		System.out.println(t);
		return t;
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

	/**
	 * Evaluates a String of commands and modifies world of turtles/background to account for this
	 * 
	 * Returns modified world.
	 */
	@Override
	public World getWorld(String commands) throws CommandException {
		Interpreter i = new Interpreter(world);
		Scope scope = new Scope(globalCommands, globalVariables, new Trajectory(), world, new Scope(true, true, true, true));
		TokenNode root = parser.parse(new TokenNode(null, new TList()), commands);
		
		for(TokenNode cmd: root.getChildren()){
			i.evaluateForTurtles(world.getActiveTurtles(), cmd, scope); // for multiple turtles
		}
		
//		System.out.println(world); // Uncomment this to test functionality of model
		return world;
	}
}
