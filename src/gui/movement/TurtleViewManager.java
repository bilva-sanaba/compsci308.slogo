package gui.movement;


import java.util.ArrayList;
import java.util.List;

import gui.executables.boxes.Palette;
import gui.executables.boxes.TurtleComboBox;
import gui.movement.utility.SingleTurtleTrajectory;
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
/**
 * Abstract class to move the TurtleImage
 * @author Bilva
 * @author Alex
 *
 */
public abstract class TurtleViewManager {
	protected TurtleView myTurtleView;
	protected boolean active;
	protected GraphicsContext graphics;
	protected List<Node> extraButtons;
	protected TurtleComboBox myTCB;
	public static final int DEFAULT_FPS = 10;
	public static final double MILLIS_PER_SECOND = 1000;
	protected int extraButtonCount;
	protected double penSize;
	protected boolean shouldDraw = true;
	protected Palette myPalette;
	/**
	 * 
	 * @param t Image of turtle
	 * @param gc GraphicsContext of Canvas being drawn on
	 * @param p Palette containing color associations to index
	 */
	public TurtleViewManager(TurtleView t,GraphicsContext gc, Palette p){
		myTurtleView=t;
		graphics = gc;
		myPalette = p;
		extraButtons = new ArrayList<Node>();

	}
	/**
	 * Depending on the type of TVM instantiated different type of buttons need to be added to GUI
	 * @return List of Buttons to be added to GUI depending 
	 */
	public List<Node> getExtraButtons(){
		return extraButtons;
	}
	/**
	 * For simplicity, method allows for easier layout determination in InputPanel
	 * @return
	 */
	public int getButtonCount(){
		return extraButtonCount;
	}
	protected Color getPenColor(SingleTurtleState uts){
		if (myPalette.evalPalette(uts.getPenColor())!=null){
			return myPalette.evalPalette(uts.getPenColor());
		}
		return myPalette.evalPalette(1);
	}
	/**
	 * Needed to allow tvm to updates turtle image based on the palette
	 * @param tcb
	 */
	public void addTurtleComboBox(TurtleComboBox tcb){
		myTCB=tcb;
	}
	/**
	 * Needed for GUI to Display info on a TVM state when hovered over
	 * @return list of labels 
	 */
	public List<Label> getStateLabels(){
		double currentXPos=+myTurtleView.getImageView().getX()+myTurtleView.getImageView().getTranslateX()+myTurtleView.getImageView().getBoundsInLocal().getWidth()/2-gui.GUI.BACKGROUND_WIDTH/2;
		double currentYPos=+myTurtleView.getImageView().getY()+myTurtleView.getImageView().getTranslateY()+myTurtleView.getImageView().getBoundsInLocal().getHeight()/2-gui.GUI.BACKGROUND_HEIGHT/2;
		Label coordinateLabel=new Label("X:"+currentXPos+"  Y:"+(-currentYPos));
		Label headingLabel=new Label(""+myTurtleView.getImageView().getRotate()%360);
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
	/**
	 * In superclass, method called to move turtle image to appropriate location
	 * @param T class containing information on all next states of a turtle
	 * @param screenWidth Width of screen 
	 * @param screenHeight Height of screen
	 */
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
	/**
	 * moves Turtles Image to location X
	 * @param xLoc
	 */
	public void setX(double xLoc){
		myTurtleView.getImageView().setX(xLoc-myTurtleView.getImageView().getBoundsInLocal().getWidth()/2);
	}
	/**
	 * moves Turtles Image to location Y
	 * @param yLoc
	 */
	public void setY(double yLoc){
		myTurtleView.getImageView().setY(yLoc-myTurtleView.getImageView().getBoundsInLocal().getHeight()/2);
	}
	/**
	 * Rotates turtles Image
	 */
	public void getRotate(){
		myTurtleView.getImageView().getRotate();
	}
	/**
	 * Unnecessary penBool should have been stored in this class to  avoid this method
	 * @return
	 */
	public boolean getPenBool(){
		return myTurtleView.getPen();
	}
	/**
	 * Needed by pensize button to set pen size
	 * @param size
	 */
	public void setPenSize(double size){
		penSize =size;
	}
	/**
	 * Should be encapsulated better so that this could have been avoided
	 * @return
	 */
	public ImageView getImage(){
		return myTurtleView.getImageView();
	}
	/**
	 * Returns class encapsulating image
	 * @return
	 */
	public TurtleView getTurtleView(){
		return myTurtleView;
	}
}