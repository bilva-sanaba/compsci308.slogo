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
public void moveTurtle(Trajectory T,double screenWidth, double screenHeight){
	for(UnmodifiableTurtleState uts:T){
		double newX=uts.getX()+screenWidth/2;
		double newY=-uts.getY()+screenHeight/2;
		double newHeading=uts.getHeading();
		boolean newPen = uts.isPenDown();
		boolean newVisibility = uts.isShowing();
		if (newPen == true){
			graphics.setStroke(myTurtleView.getPenColor());
			graphics.strokeLine(myTurtleView.getImage().getX()+myTurtleView.getImage().getBoundsInParent().getWidth()/2, myTurtleView.getImage().getY()+myTurtleView.getImage().getBoundsInParent().getHeight()/2, newX+myTurtleView.getImage().getBoundsInParent().getWidth()/2, newY+myTurtleView.getImage().getBoundsInParent().getHeight()/2);
		}
		myTurtleView.setX(newX);
		myTurtleView.setY(newY);
		myTurtleView.setVisibility(newVisibility);
		myTurtleView.getImage().setRotate(newHeading);
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
public TurtleView getTurtleView(){
	return myTurtleView;
}
}
