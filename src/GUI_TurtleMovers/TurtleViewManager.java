package GUI_TurtleMovers;


import java.util.ArrayList;
import java.util.List;

import GUI.TurtleComboBox;
import GUI_Objects.Palette;
import model.configuration.CompositeTurtleState;
import model.configuration.SingleTurtleState;


import javafx.scene.Node;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import model.configuration.Trajectory;
import model.configuration.UnmodifiableTurtleComposite;

public abstract class TurtleViewManager {
	protected TurtleView myTurtleView;
	protected boolean active;
	protected GraphicsContext graphics;
	protected List<Node> extraButtons;
	protected TurtleComboBox myTCB;
    public static final int DEFAULT_FPS = 10;
    public static final double MILLIS_PER_SECOND = 1000;
    protected double penSize;
    protected Palette myPalette;
    
	public TurtleViewManager(TurtleView t,GraphicsContext gc, Palette p){
		myTurtleView=t;
		graphics = gc;
		myPalette = p;
		extraButtons = new ArrayList<Node>();
//		activeClick();
	}
//	public boolean isActive(){
//		return active;
//	}
	public List<Node> getExtraButtons(){
		return extraButtons;
	}
//	private void activeClick(){
//		myTurtleView.getImage().setOnMouseClicked(e -> active=!active);
//	}
	protected Color getPenColor(SingleTurtleState uts){
		if (myPalette.evalPalette(uts.getPenColor())!=null){
			return myPalette.evalPalette(uts.getPenColor());
		}
		return myPalette.evalPalette(1);
	}
	public void addTurtleComboBox(TurtleComboBox tcb){
		myTCB=tcb;
	}
	public List<Label> getStateLabels(){
		double currentXPos=+myTurtleView.getImage().getX()+myTurtleView.getImage().getTranslateX()+myTurtleView.getImage().getBoundsInLocal().getWidth()/2-GUI.GUI.BACKGROUND_WIDTH/2;
		double currentYPos=+myTurtleView.getImage().getY()+myTurtleView.getImage().getTranslateY()+myTurtleView.getImage().getBoundsInLocal().getHeight()/2-GUI.GUI.BACKGROUND_HEIGHT/2;
		Label coordinateLabel=new Label("X:"+currentXPos+"  Y:"+(-currentYPos));
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
	public void moveTurtle(SingleTurtleTrajectory T,double screenWidth, double screenHeight){
		for(SingleTurtleState uts:T){
			draw(uts,screenWidth,screenHeight);
			moveLocation(uts,screenWidth,screenHeight);
			rotate(uts);
			changeVisibility(uts);
			setShape(uts);
			
		}
	}
	protected void setShape(SingleTurtleState uts){
		//myTCB.getTurtleChooser().getSelectionModel().select(uts.getShape());
		myTurtleView.setShape(myTCB.getTurtleChooser().getItems().get(uts.getShape()));
	}
	protected abstract void draw(SingleTurtleState uts,double screenWidth, double screenHeight);
	protected abstract void moveLocation(SingleTurtleState uts,double screenWidth, double screenHeight);
	protected abstract void rotate(SingleTurtleState uts);
	protected abstract void changeVisibility(SingleTurtleState uts);
	
	protected static class Location {
		double x;
		double y;
	}
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
	public void setPenSize(double size){
		penSize =size;
	}
	public ImageView getImage(){
		return myTurtleView.getImage();
	}
	public TurtleView getTurtleView(){
		return myTurtleView;
	}
}