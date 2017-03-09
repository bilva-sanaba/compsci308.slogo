package model;

import java.util.Collection;
import java.util.Set;

import configuration.SingleTurtleState;
import configuration.Trajectory;
import configuration.UnmodifiableTurtleComposite;

/**
 * This class contains all of the Turtles 
 * 
 * @author DhruvKPatel
 *
 */
public class World {
	private TurtleManager turtles;
	
	private int backgroundColor;
	
	public World(Trajectory turtleTrajectory){
		turtles = new TurtleManager(turtleTrajectory);
		backgroundColor = 0;
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
	 * Returns a list of all active turtles
	 */
	public Collection<SingleTurtleState> getActiveTurtles(){
		return turtles.getActiveTurtles();
	}
	
	public Collection<Integer> getActiveTurtleIndicies(){
		return turtles.getActiveTurtleIndicies();
	}
	
	
	public Collection<SingleTurtleState> getAllTurtles(){
		return turtles.getAllTurtles();
	}
	
	public Collection<Integer> getAllTurtleIndicies(){
		return turtles.getAllTurtleIndicies();
	}

	/**
	 * Sets background color index of world
	 * @param newColor
	 */
	public void setBackground(int newColor){
		backgroundColor = newColor;
	}
	
	/**
	 * Gets background color index of world
	 * @param newColor
	 */
	public int getBackground(){
		return backgroundColor;
	}
	
	/**
	 * Returns string of all existing turtles
	 */
	public String toString(){
		String w = "*********World********\n";
//		for(UnmodifiableTurtleComposite t : turtles.getTrajectory()){
//			w += t;
//		}
		w += turtles.getTrajectory();
		w += "**********************\n";
		return w;
	}
}
