package GUI;

import configuration.Trajectory;
import configuration.UnmodifiableTurtleState;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;

public class TurtleViewManager {
	private TurtleView myTurtleView;
	private GraphicsContext graphics;
    public static final int DEFAULT_FPS = 10;
    public static final double MILLIS_PER_SECOND = 1000;
	public TurtleViewManager(TurtleView t,GraphicsContext gc){
	myTurtleView=t;
	graphics = gc;
	}
public void moveTurtle(Trajectory T){
	for(UnmodifiableTurtleState uts:T){
		double newX=uts.getX();
		double newY=uts.getY();
		double newHeading=uts.getHeading();
		boolean newPen = uts.isPenDown();
		boolean newVisibility = uts.isShowing();
		if (newPen == true){
			graphics.setStroke(myTurtleView.getPenColor());
			graphics.strokeLine(myTurtleView.getImage().getX()+myTurtleView.getImage().getBoundsInLocal().getWidth()/2, myTurtleView.getImage().getY()+myTurtleView.getImage().getBoundsInLocal().getHeight()/2, newX, newY);
		}
		myTurtleView.setX(newX);
		myTurtleView.setY(newY);
		myTurtleView.setVisibility(newVisibility);
		myTurtleView.getImage().setRotate(newHeading-myTurtleView.getImage().getRotate());
	}
}

public void update(){}
public void setX(double xLoc){
	myTurtleView.getImage().setX(xLoc);
}
public void setY(double yLoc){
	myTurtleView.getImage().setY(yLoc);
}
public ImageView getImage(){
	return myTurtleView.getImage();
}
public TurtleView getTurtleView(){
	return myTurtleView;
}
}
