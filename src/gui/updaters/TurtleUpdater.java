package gui.updaters;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import gui.GUI;
import gui.movement.TurtleRegularMover;
import gui.movement.TurtleViewManager;
import gui.movement.utility.Adapter;
import gui.movement.utility.SingleTurtleTrajectory;
import model.UnmodifiableWorld;
import model.configuration.Trajectory;
/**
 * Updates position of all Turtles
 * @author Bilva
 *
 */
public class TurtleUpdater {
	private Map<Integer,TurtleViewManager> myTurtles;
	public TurtleUpdater(){

	}
	/**
	 * Iterates through all existent Turtles and moves them based on the current world
	 * @param w
	 * @param a
	 */
	public void moveTurtles(Trajectory w,Map<Integer,TurtleViewManager> a){
		myTurtles = a;
		Map<Integer,SingleTurtleTrajectory> myMoves = Adapter.getSingleTrajectories(w);
		for (int i : myMoves.keySet()){
			myTurtles.get(i).moveTurtle(myMoves.get(i),GUI.BACKGROUND_WIDTH,GUI.BACKGROUND_HEIGHT);	
		}	
	}
}
