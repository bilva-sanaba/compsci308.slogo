package model;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * This class contains all of the Turtles 
 * 
 * @author DhruvKPatel
 *
 */
public class World {
	private Map<Integer, Turtle> turtles;
	private Turtle currentlyActiveTurtle;
	private int backgroundColor;
	private boolean variableChanged;
	public World(){
		turtles = new HashMap<>();		
		turtles.put(0, new Turtle()); // initial default turtle
		currentlyActiveTurtle = turtles.get(0);
		backgroundColor = 0;
	}
	
	/**
	 * Returns list of all turtles that exist in world
	 */
	public Map<Integer, Turtle> getTurtles(){
		return turtles;
	}
	public boolean isVariableChanged(){
		return variableChanged;
	}
	
	/**
	 * Returns the Turtle at a certain ID
	 * 
	 * If ID does not exist, will return null
	 * @param turtleID
	 * @return
	 */
	public Turtle getTurtle(int turtleID){
		return turtles.get(turtleID);
	}
	
	/**
	 * Makes all turtles with indicies contained int the list active.
	 * 
	 * Note: if turtle index doesn't exist in world, will add turtle to world as active will default state
	 * @param Indecies
	 */
	public void setActiveTurtles(Set<Integer> Indicies){
		for(Turtle t: turtles.values()){
			t.setActive(false);
		}
		for(Integer index : Indicies){
			if(turtles.get(index) == null){
				turtles.put(index, new Turtle());
			}
			else{
				turtles.get(index).setActive(true);
			}
		}
	}
	
	/**
	 * Returns a list of all active turtles
	 */
	public Map<Integer, Turtle> getActiveTurtles(){
		Map<Integer, Turtle> actives = new HashMap<>();
		
		for(Integer index : turtles.keySet()){
			Turtle t = turtles.get(index);
			if(t.isActive()) actives.put(index, t);
		}
		return actives;
	}
	
	/**
	 * Returns singular active turtle at any point in time
	 * 
	 * (defaults to turtle 0)
	 */
	public Turtle getCurrentlyActiveTurtle(){
		return currentlyActiveTurtle;
	}
	
	/**
	 * Sets singular active turtle at any point in time
	 */
	public void setCurrentlyActiveTurtle(Turtle t){
		currentlyActiveTurtle = t;
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
		for(Integer i : this.getTurtles().keySet()){
			w += String.format("\nTurtle: %d, Active: %b\n", i, getTurtle(i).isActive());
			w += getTurtle(i).getTrajectory();
		}
		w += "**********************\n";
		return w;
	}
}
