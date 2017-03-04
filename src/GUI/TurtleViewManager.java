package GUI;

import configuration.Trajectory;
import configuration.UnmodifiableTurtleState;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.transform.Rotate;

public class TurtleViewManager {
	private TurtleView myTurtleView;
	private GraphicsContext graphics;
	private double currentX=0;
	private double currentY=0;
    public static final int DEFAULT_FPS = 10;
    public static final double MILLIS_PER_SECOND = 1000;
    
	public TurtleViewManager(TurtleView t,GraphicsContext gc){
	myTurtleView=t;
	graphics = gc;
	}
public void moveTurtle(Trajectory T,double screenWidth, double screenHeight){
	double oldX=myTurtleView.getImage().getX()+myTurtleView.getImage().getBoundsInLocal().getWidth()/2;
	
	double oldY=myTurtleView.getImage().getY()+myTurtleView.getImage().getBoundsInLocal().getHeight()/2;
	for(UnmodifiableTurtleState uts:T){
		double penX=uts.getX()+screenWidth/2;
		double penY=-uts.getY()+screenHeight/2;
		double newX=uts.getX()+screenWidth/2-myTurtleView.getImage().getBoundsInLocal().getWidth()/2;
		double newY=-uts.getY()+screenHeight/2-myTurtleView.getImage().getBoundsInLocal().getHeight()/2;
		double newHeading=uts.getHeading();
		boolean newPen = uts.isPenDown();
		boolean newVisibility = uts.isShowing();
		if (newPen == true){
			graphics.setStroke(myTurtleView.getPenColor());

		graphics.strokeLine(oldX, oldY, penX, penY);

		}
		
		myTurtleView.setX(newX);
		myTurtleView.setY(newY);
		oldX=penX;
		oldY=penY;
		myTurtleView.setVisibility(newVisibility);
		myTurtleView.getImage().setRotate(newHeading);
	}
}

public void setX(double xLoc){
	myTurtleView.getImage().setX(xLoc-myTurtleView.getImage().getBoundsInLocal().getWidth()/2);
}
public void setY(double yLoc){
	myTurtleView.getImage().setY(yLoc-myTurtleView.getImage().getBoundsInLocal().getHeight()/2);
}
public ImageView getImage(){
	return myTurtleView.getImage();
}
public TurtleView getTurtleView(){
	return myTurtleView;
}
}
