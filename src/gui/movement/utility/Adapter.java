package gui.movement.utility;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import model.configuration.SingleTurtleState;
import model.configuration.Trajectory;
import model.configuration.UnmodifiableTurtleComposite;

public class Adapter {
public Adapter(){
	
}
/**
 * Converts backend data into data front end can use
 * @param t
 * @return
 */
public static Map<Integer,SingleTurtleTrajectory>getSingleTrajectories(Trajectory t){
	Map<Integer, SingleTurtleTrajectory> map=new HashMap<Integer,SingleTurtleTrajectory>();
	UnmodifiableTurtleComposite last=t.getLast();
	
	ArrayList<Integer>presentTurtles=new ArrayList<Integer>();
	for(SingleTurtleState turtle:last){
		int i=turtle.getID();
		map.put(i, new SingleTurtleTrajectory(new ArrayList<SingleTurtleState>()));
	}
	
	for(UnmodifiableTurtleComposite group:t){
	for(Integer i:map.keySet()){
		map.get(i).addLast(new SingleTurtleState(0,0,0,false,false,-1,false,0,0,0, 0)); //includes stampcount
	}
		for(SingleTurtleState turtle:group){
			int i=turtle.getID();
			map.get(i).removeLast();
			map.get(i).addLast(turtle);
		}
	}
return map;
}
}
