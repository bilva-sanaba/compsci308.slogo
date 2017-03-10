package model.configuration;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

/**
 * This class Adapts the "Trajectory" objects,
 * which is essentially a list of multiple turtle 
 * states to be contained by "World", to make 
 * adding new turtles easier
 * 
 * @author DhruvKPatel
 *
 */
public class TurtleManager {
	private Trajectory turtles;

	/**
	 * Constructs an empty trajectory of turtles
	 */
	public TurtleManager(Trajectory t) {
		turtles = t;
		CompositeTurtleState initialState = new CompositeTurtleState(new SingleTurtleState(0)); // Initial state begins with 1 default turtle state at ID = 0
		turtles.addLast(initialState);
	}
	

	/**
	 * Makes all turtles with indicies contained int the list active.
	 * 
	 * Note: if turtle index doesn't exist in world, will add turtle to world as active will default state
	 * @param Indecies
	 */
	public void setActiveTurtles(Collection<Integer> Indicies){
		UnmodifiableTurtleComposite past = turtles.getLast();
		CompositeTurtleState current = past.getCompositeCopy();
		
		for(SingleTurtleState turtle : getActiveTurtles()){
			current.getState(turtle.getID()).setActive(false);
		}
		
		for(int id : Indicies){
			if(current.getState(id) == null) current.setState(id, new SingleTurtleState(id));
			current.getState(id).setActive(Indicies.contains(id));
		}
		turtles.addLastComposite(current);
	}
	

	/**
	 * Returns a collection of all active turtles
	 * @return
	 */
	public Collection<SingleTurtleState> getActiveTurtles(){
		Set<SingleTurtleState> actives = new HashSet<>();
		for(SingleTurtleState turtle : turtles.getLast()){
			if(turtle.isActive()) actives.add(turtle);
		}
		return actives;
	}
	
	/**
	 * Returns a collection of all active turtle indicies
	 */
	public Collection<Integer> getActiveTurtleIndicies(){
		Set<Integer> indicies = new HashSet<>();
		for(SingleTurtleState turtle : turtles.getLast()){
			if(turtle.isActive()) indicies.add(turtle.getID());
		}
		return indicies;
	}
	
	/**
	 * Returns a collection of all turtles
	 */
	public Collection<SingleTurtleState> getAllTurtles(){
		return turtles.getLast().getCompositeCopy().getAllStates().values();
	}
	
	/**
	 * Returns a collection of all turtle indicies
	 */
	public Collection<Integer> getAllTurtleIndicies(){
		return turtles.getLast().getCompositeCopy().getAllStates().keySet();
	}
	
	/**
	 * Returns full trajectory
	 */
	public Trajectory getTrajectory(){
		return turtles;
	}

}
