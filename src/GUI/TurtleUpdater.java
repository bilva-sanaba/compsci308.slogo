package GUI;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import GUI_TurtleMovers.Adapter;
import GUI_TurtleMovers.SingleTurtleTrajectory;
import GUI_TurtleMovers.TurtleRegularMover;
import GUI_TurtleMovers.TurtleViewManager;

import model.UnmodifiableWorld;
import model.configuration.Trajectory;

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
			myTurtles.get(i).moveTurtle(myMoves.get(i),GUI.BACKGROUND_WIDTH,GUI.BACKGROUND_HEIGHT);	
		}	
}
}
