package GUI_TurtleMovers;

import java.util.ArrayList;
import java.util.List;


public class MovementEvaluator {
	public List<Location> evaluate(double oldX, double oldY, double penX, double penY, double screenWidth, double screenHeight){
		List<Location> returnList = new ArrayList<Location>(); 
		Location oldLocation = null;
		oldLocation.x =oldX;
		oldLocation.y = oldY;
		returnList.add(oldLocation);
		while (true){
			Location tempLoc = null;
			tempLoc.x = penX;
			tempLoc.y = penY;
			
		}
		newLocation.x = penX;
		newLocation.y = penY;
		if (penX>screenWidth){
			newLocation.x=screenWidth;
		}
	
		returnList.add(e)
		return returnList;
		
		
	}
	private static class Location {
		double x;
		double y;
	}
}
