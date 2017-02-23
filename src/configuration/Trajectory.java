package configuration;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * Holds a trajectory of Unmodifiable TurtleStates
 * 
 * The purpose of this class is to transfer knowledge of
 * Turtle's motion from the model to the view. The first item
 * in a trajectory will always be the Turtle's initial state, 
 * and the last item will be its final State.
 * 
 * Features:
 * - Once a TurtleState is added to a trajectory,
 * it is no longer modifiable. This is to prevent further tampering.
 * 
 * - Adding the same state to a trajectory twice in a row will only create
 * one new trajectory state (reduces unecessary repeating).
 * 
 * @author DhruvKPatel
 */
public class Trajectory implements Iterable<UnmodifiableTurtleState> {
	
	ArrayList<UnmodifiableTurtleState> trajectory;
	
	/**
	 * Constructs a Trajectory with no initial states.
	 * Use methods to add to end of trajectory
	 */
	public Trajectory(){
		trajectory = new ArrayList<UnmodifiableTurtleState>();
	}
	
	/**
	 * Adds TurtleState to end of trajectory if it is different
	 * than the previous
	 * @param nextState
	 */
	public void addLast(TurtleState nextState){
		if(!nextState.equals(getLast()))
			trajectory.add((UnmodifiableTurtleState) nextState.getCopy());	
	}
	
	/**
	 * Returns last state in Trajectory.
	 * @return
	 */
	public UnmodifiableTurtleState getLast(){
		if(trajectory.size() == 0) return null;
		return trajectory.get(trajectory.size() - 1);
	}
	
	/**
	 * Returns size of trajectory
	 */
	public int size(){
		return trajectory.size();
	}
	
	/**
	 * This method exists to allow the class to be iterable
	 */
	@Override
	public Iterator<UnmodifiableTurtleState> iterator() {
		return trajectory.iterator();
	}
	
	/**
	 * To help with printing/debugging
	 */
	public String toString(){
		String s = "______Trajectory______";
		for(UnmodifiableTurtleState state: this) s += "\n" + state.toString();
		s += "______________________";
		return s;
	}
}
