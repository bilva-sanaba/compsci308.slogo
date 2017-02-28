package GUI;

import configuration.Trajectory;
import configuration.UnmodifiableTurtleState;
import javafx.scene.image.ImageView;

public class TurtleViewManager {
	private TurtleView myTurtleView;
	
	public TurtleViewManager(TurtleView t){
	myTurtleView=t;
	}
public void moveTurtle(Trajectory T){
	for(UnmodifiableTurtleState uts:T){
		double newX=uts.getX();
		double newY=uts.getY();
		double newHeading=uts.getHeading();
	}
}
public void setX(double xLoc){
	myTurtleView.getImage().setX(xLoc);
}
public void setY(double yLoc){
	myTurtleView.getImage().setY(yLoc);
}
public ImageView getImage(){
	return myTurtleView.getImage();
}
}
