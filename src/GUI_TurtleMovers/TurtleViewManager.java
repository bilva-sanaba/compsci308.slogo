package GUI_TurtleMovers;


import java.util.ArrayList;
import java.util.List;

import configuration.Trajectory;
import configuration.UnmodifiableTurtleState;
import javafx.scene.Node;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;

public abstract class TurtleViewManager {
	protected TurtleView myTurtleView;
	protected GraphicsContext graphics;
	protected List<Node> extraButtons;
    public static final int DEFAULT_FPS = 10;
    public static final double MILLIS_PER_SECOND = 1000;
    
	public TurtleViewManager(TurtleView t,GraphicsContext gc){
		myTurtleView=t;
		graphics = gc;
		extraButtons = new ArrayList<Node>();
	}
	public List<Node> getExtraButtons(){
		return extraButtons;
	}
	
	public List<Label> getStateLabels(){
		double currentXPos=myTurtleView.getImage().getTranslateX();
		double currentYPos=-myTurtleView.getImage().getTranslateY();
		Label coordinateLabel=new Label("X:"+currentXPos+"  Y:"+currentYPos);
		Label headingLabel=new Label(""+myTurtleView.getImage().getRotate()%360);
		Label penUpLabel=new Label("" +getPenBool());
		ArrayList<Label>stateLabels=new ArrayList<Label>();
		stateLabels.add(coordinateLabel);
		stateLabels.add(headingLabel);
		stateLabels.add(penUpLabel);
		arrangeLabels(stateLabels);
		return stateLabels;
	}
	private void arrangeLabels(List<Label>labels){
		for(int i=0;i<labels.size();i++){
			labels.get(i).setLayoutY(10*i);
		}
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
	public void getRotate(){
		myTurtleView.getImage().getRotate();
	}
	public boolean getPenBool(){
		return myTurtleView.getPen();
	}
	public ImageView getImage(){
		return myTurtleView.getImage();
	}
	public TurtleView getTurtleView(){
		return myTurtleView;
	}
}