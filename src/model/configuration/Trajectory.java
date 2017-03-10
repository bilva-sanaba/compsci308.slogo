package model.configuration;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Holds a single turtle's trajectory of Unmodifiable TurtleStates
 * 
 * @author DhruvKPatel
 */
public class Trajectory implements Iterable<UnmodifiableTurtleComposite> {
	
	ArrayList<UnmodifiableTurtleComposite> fullTrajectory;
	ArrayList<UnmodifiableTurtleComposite> trajectoryAdditions;
	
	/**
	 * Constructs a Trajectory from a list of UnmodifiableTurtleStates
	 */
	public Trajectory(List<UnmodifiableTurtleComposite> states){
		fullTrajectory = new ArrayList<>(states);
		trajectoryAdditions = new ArrayList<>(); // Insert "states" into parameter if first trajectory should contain initial state
	}
	
	/**
	 * Copy constructor
	 * @param original
	 */
	public Trajectory (Trajectory original){
		this(original.fullTrajectory);
	}
	
	/**
	 * Constructs a Trajectory with no initial states.
	 * Use methods to add to end of trajectory
	 */
	public Trajectory(){
		this(new ArrayList<UnmodifiableTurtleComposite>());
	}
	
	/**
	 * Adds TurtleState to end of trajectory if it is different
	 * than the previous
	 * @param nextState
	 */
	public void addLastComposite(UnmodifiableTurtleComposite nextState){
		if(!nextState.equals(getLast()))
			fullTrajectory.add((UnmodifiableTurtleComposite)nextState.getCompositeCopy());	
			trajectoryAdditions.add((UnmodifiableTurtleComposite)nextState.getCompositeCopy());
	}
	
	public void addLast(TurtleState nextState){
		if(nextState instanceof CompositeTurtleState){
			addLastComposite((UnmodifiableTurtleComposite)nextState);
		}
	}
	
	/**
	 * Returns a trajectory made up of most recent additions to trajectory
	 * 
	 * Note: when this is called, it resets its count of most recent additions.
	 */
	public Trajectory getMostRecentAdditions(){
		Trajectory newTraj =  new Trajectory(trajectoryAdditions);
		trajectoryAdditions.clear();
		return newTraj;		
	}
	
	/**
	 * Returns last state in Trajectory.
	 * @return
	 */
	public UnmodifiableTurtleComposite getLast(){
		if(fullTrajectory.size() == 0) return null;
		return fullTrajectory.get(fullTrajectory.size() - 1);
	}
	
	/**
	 * Clears entire trajectory
	 */
	public void clear(){
		fullTrajectory.clear();
		trajectoryAdditions.clear();
		addLast(new CompositeTurtleState(new SingleTurtleState(0))); // adds one state at index 0
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
	public Iterator<UnmodifiableTurtleComposite> iterator() {
		return fullTrajectory.iterator();
	}
	
	/**
	 * To help with printing/debugging
	 */
	public String toString(){
		String s = "______Trajectory______";
//		for(UnmodifiableTurtleComposite state: this) s += "\n" + state.toString(); // prints all values
		if(getLast() == null) return s;
		s += "\n" + getLast().toString(); // prints last values
		s += "______________________\n";
		return s;
	}
}
