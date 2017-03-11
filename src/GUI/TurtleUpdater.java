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
	private Map<Integer,TurtleViewManager> myTurtles;
	TurtleUpdater(){
		
	}
	public void moveTurtles(Trajectory w,Map<Integer,TurtleViewManager> a){
		myTurtles = a;
//		Trajectory worldTrajectories= w.getTrajectoryUpdates();
//		Trajectory nextTrajectory = worldTrajectories.getMostRecentAdditions();
////		System.out.println(worldTrajectories.getLast());
		Map<Integer,SingleTurtleTrajectory> myMoves = Adapter.getSingleTrajectories(w);
		

		for (int i : myMoves.keySet()){

			myTurtles.get(i).moveTurtle(myMoves.get(i),GUI.BACKGROUND_WIDTH,GUI.BACKGROUND_HEIGHT);	
		}	
}
}
