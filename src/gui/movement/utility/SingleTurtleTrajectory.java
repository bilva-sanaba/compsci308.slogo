package gui.movement.utility;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import model.configuration.SingleTurtleState;
/**
 * Data class which stores info for where a turtle will go
 * @author Bilva
 *
 */
public class SingleTurtleTrajectory implements Iterable<SingleTurtleState> {
	 	
	 	ArrayList<SingleTurtleState> fullTrajectory;
	 	ArrayList<SingleTurtleState> trajectoryAdditions;
	 	
	 	/**
	 	 * Constructs a Trajectory from a list of SingleTurtleStates
	 	 */
	 	public SingleTurtleTrajectory(List<SingleTurtleState> states){
	 		fullTrajectory = new ArrayList<>(states);
	 		trajectoryAdditions = new ArrayList<>(); // Insert "states" into parameter if first trajectory should contain initial state
	 	}
	 	
	 	/**
	 	 * Copy constructor
	 	 * @param original
	 	 */
	 	public SingleTurtleTrajectory(SingleTurtleTrajectory original){
	 		this(original.fullTrajectory);
	 	}
	 	
	 	/**
	 	 * Constructs a Trajectory with no initial states.
	 	 * Use methods to add to end of trajectory
	 	 */
	 	
	 	
	 	/**
	 	 * Adds TurtleState to end of trajectory if it is different
	 	 * than the previous
	 	 * @param nextState
	 	 */
	 	public void addLast(SingleTurtleState nextState){
	 		if(!nextState.equals(getLast()))
	 			fullTrajectory.add((SingleTurtleState)nextState.getModifiableCopy());	
	 			trajectoryAdditions.add((SingleTurtleState)nextState.getModifiableCopy());
	 	}
	 	
	 	/**
	 	 * Returns a trajectory made up of most recent additions to trajectory
	 	 * 
	 	 * Note: when this is called, it resets its count of most recent additions.
	 	 */
	 	public SingleTurtleTrajectory getMostRecentAdditions(){
	 		SingleTurtleTrajectory newTraj =  new SingleTurtleTrajectory(trajectoryAdditions);
	 		trajectoryAdditions.clear();
//	 		trajectoryAdditions.add(getLast());// Uncomment if frontend wants start state every time
	 		return newTraj;		
	 	}
	 	
	 	/**
	 	 * Returns last state in Trajectory.
	 	 * @return
	 	 */
	 	public SingleTurtleState getLast(){
	 		if(fullTrajectory.size() == 0) return null;
	 		return fullTrajectory.get(fullTrajectory.size() - 1);
	 	}
	 	public void removeLast(){
	 		if(fullTrajectory.size() != 0){
	 			fullTrajectory.remove(fullTrajectory.size()-1);
	 		}
	 	}
	 	
	 	/**
	 	 * Clears entire trajectory
	 	 */
	 	public void clear(){
	 		fullTrajectory = new ArrayList<SingleTurtleState>();
	 	}
	 	
	 	/**
	 	 * Returns size of trajectory
	 	 */
	 	public int size(){
	 		return fullTrajectory.size();
	 	}
	 	
	 	/**
	 	 * This method exists to allow the class to be iterable
	 	 */
	 	@Override
	 	public Iterator<SingleTurtleState> iterator() {
	 		return fullTrajectory.iterator();
	 	}
	 	
	 	/**
	 	 * To help with printing/debugging
	 	 */
	 	public String toString(){
	 		String s = "______Trajectory______";
	 		for(SingleTurtleState state: this) s += "\n" + state.toString();
	 		s += "______________________\n";
	 		return s;
	 	}
	 }

