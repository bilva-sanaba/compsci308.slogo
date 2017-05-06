package model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import model.commands.CommandFactory;
import model.configuration.SingleTurtleState;
import model.configuration.Trajectory;
import model.configuration.TurtleManager;
import model.tokens.VariableContainer;

/**
 * This class contains all of the Turtles 
 * 
 * @author DhruvKPatel
 *
 */
public class World implements UnmodifiableWorld{
	private TurtleManager turtles;
	private VariableContainer variables;
	private CommandFactory commands;

	private boolean isBackgroundSet=false;
	private int backgroundIndex;
	private boolean shouldClear;
	private Map<Integer, ArrayList<Integer>> paletteUpdates;
	

	
	public World(Trajectory turtleTrajectory, VariableContainer variables, CommandFactory commands){
		turtles = new TurtleManager(turtleTrajectory);
		backgroundIndex = 0;
		shouldClear = false;
		paletteUpdates = new HashMap<>();
		this.variables = variables;
		this.commands = commands;
	}
	
	/**
	 * Returns Trajectory composed of all turtle
	 * trajectories
	 */
	public TurtleManager getTurtles(){
		return turtles;
	}	
	

	/**
	 * Makes all turtles with indicies contained int the list active.
	 * 
	 * Note: if turtle index doesn't exist in world, will add turtle to world as active will default state
	 * @param Indecies
	 */
	public void setActiveTurtles(Collection<Integer> indicies){
		turtles.setActiveTurtles(indicies);
	}
	
	/**
	 * Returns a collection of all active turtles
	 */
	public Collection<SingleTurtleState> getActiveTurtles(){
		return turtles.getActiveTurtles();
	}
	
	/**
	 * Returns a collection of all active turtle indicies
	 */
	public Collection<Integer> getActiveTurtleIndicies(){
		return turtles.getActiveTurtleIndicies();
	}
	
	/**
	 * Returns a collection of all turtles
	 * @return
	 */
	public Collection<SingleTurtleState> getAllTurtles(){
		return turtles.getAllTurtles();
	}
	
	/**
	 * Returns a collection of all active turtle indicies
	 */
	public Collection<Integer> getAllTurtleIndicies(){
		return turtles.getAllTurtleIndicies();
	}

	/**
	 * Sets background color index of world
	 * @param newColor
	 */
	public void setBackground(int newColor){
		backgroundIndex = newColor;
		isBackgroundSet = true;
	}
	
	/**
	 * Gets background color index of world
	 * @param newColor
	 */
	public int getBackground(){
		return backgroundIndex;
	}
	/**
	 * 
	 */
	public boolean isBackgroundSet(){
		return isBackgroundSet;
	}
	/**
	 * Adds new pallete index
	 */
	public void addPalleteUpdate(int index, int r, int g, int b){
		this.paletteUpdates.put(index, new ArrayList<Integer>(Arrays.asList(r, g, b)));
	}
	
	/**
	 * Gets pallete updates
	 */
	public Map<Integer, ArrayList<Integer>> getPalleteUpdates(){
		Map<Integer, ArrayList<Integer>> updates = new HashMap<>(paletteUpdates);
		paletteUpdates.clear();
		return updates;
	}
	
	/**
	 * Returns whether world should clear
	 */
	public boolean shouldClear(){
		boolean result = shouldClear;
		shouldClear = false;
		return result;
	}
	
	/**
	 * Clears trajectory in backend and updates status
	 */
	public void clear(){
		shouldClear = true;
		turtles.getTrajectory().clear();
	}	
	
	/**
	 * Returns string of all existing turtles
	 */
	public String toString(){
		String w = "*********World********\n";
		w += turtles.getTrajectory();
		w += variables;
		w += commands;
		w += "**********************\n";
		return w;
	}

	@Override
	public Trajectory getTrajectoryUpdates() {
		return turtles.getTrajectory().getMostRecentAdditions();
	}

	@Override
	public VariableContainer getVariables() {
		return variables;
	}

	@Override
	public Collection<String> getCommandNames() {
		return commands.getUserDefinedNames();
	}
}
