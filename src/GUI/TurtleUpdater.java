package GUI;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import GUI_TurtleMovers.TurtleRegularMover;
import GUI_TurtleMovers.TurtleViewManager;
import configuration.CompositeTurtleState;
import configuration.SingleTurtleState;
import configuration.Trajectory;
import configuration.UnmodifiableTurtleComposite;
import model.UnmodifiableWorld;

public class TurtleUpdater {
	private Map<Integer,TurtleRegularMover> myTurtles;
	TurtleUpdater(){
		
	}
	private void moveTurtles(UnmodifiableWorld w,Map<Integer,TurtleRegularMover> a){
		myTurtles = a;
		Trajectory worldTrajectories= w.getTrajectoryUpdates();
		Trajectory nextTrajectory = worldTrajectories.getMostRecentAdditions();
		Map<Integer,SingleTurtleTrajectory> myMoves = Adapter.getSingleTrajectories(nextTrajectory);
		
		for (int i=0; i< myMoves.size(); i++){
			myTurtles.get(i).moveTurtle(myMoves.get(i));	
		}	
}
