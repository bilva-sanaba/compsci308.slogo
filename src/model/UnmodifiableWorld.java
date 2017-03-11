package model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;

import model.configuration.Trajectory;
import model.tokens.VariableContainer;

/**
 * This class contains all information about world being sent to frontend
 * 
 * @author DhruvKPatel
 *
 */
public interface UnmodifiableWorld {

	/**
	 * Returns Trajectory composed of all turtle
	 * trajectories
	 */
	public Trajectory getTrajectoryUpdates();

	/**
	 * Gets background color index of world
	 * @param newColor
	 */
	public int getBackground();
	
	/**
	 * Gets pallete updates
	 */
	public Map<Integer, ArrayList<Integer>> getPalleteUpdates();
	
	/**
	 * Returns whether world should clear this round
	 */
	public boolean shouldClear();
	
	/**
	 * Returns global variables
	 */
	public VariableContainer getVariables();
	
	/**
	 * Returns all user-set commands
	 */
	public Collection<String> getCommandNames();
	public boolean isBackgroundSet();
	public Collection<Integer> getActiveTurtleIndicies();
}
