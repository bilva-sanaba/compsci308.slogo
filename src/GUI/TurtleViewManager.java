package GUI;


import configuration.Trajectory;
import configuration.UnmodifiableTurtleState;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.ImageView;

public abstract class TurtleViewManager {
	protected TurtleView myTurtleView;
	protected GraphicsContext graphics;
    public static final int DEFAULT_FPS = 10;
    public static final double MILLIS_PER_SECOND = 1000;
    
	public TurtleViewManager(TurtleView t,GraphicsContext gc){
		myTurtleView=t;
		graphics = gc;
	}
	public void moveTurtle(Trajectory T,double screenWidth, double screenHeight){
		for(UnmodifiableTurtleState uts:T){
			draw(uts,screenWidth,screenHeight);
			moveLocation(uts,screenWidth,screenHeight);
			rotate(uts);
			changeVisibility(uts);
		}
	}
	protected abstract void draw(UnmodifiableTurtleState uts,double screenWidth, double screenHeight);
	protected abstract void moveLocation(UnmodifiableTurtleState uts,double screenWidth, double screenHeight);
	protected abstract void rotate(UnmodifiableTurtleState uts);
	protected abstract void changeVisibility(UnmodifiableTurtleState uts);
	
	
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