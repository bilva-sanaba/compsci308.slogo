package GUI_TurtleMovers;

import java.util.ArrayList;
import java.util.List;

import GUI.GUI;
import GUI_Objects.ButtonMaker;
import configuration.Trajectory;
import configuration.UnmodifiableTurtleComposite;
import javafx.animation.FadeTransition;
import javafx.animation.PathTransition;
import javafx.animation.RotateTransition;
import javafx.animation.SequentialTransition;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.beans.value.ObservableValueBase;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.shape.LineTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import javafx.scene.transform.Rotate;
import javafx.util.Duration;

public class TurtleAnimator extends TurtleViewManager{
	private static final int DEFAULT_SPEED = 1500;
	private int speed=DEFAULT_SPEED;
	private ButtonMaker buttonMaker = new ButtonMaker();
	private Slider slider;
	private Label speedLabel;
	private static final double DEFAULT_PEN_SIZE = 4;
	private double penSize = DEFAULT_PEN_SIZE;
	private TextField penSizeButton;
	private double currentXPos;
	private double currentYPos;
	private double currentRotate=0;
	private double currentOpacity=1.0;
	private boolean skipFirst = false;
	private SequentialTransition xy = new SequentialTransition();
	public TurtleAnimator(TurtleView t, GraphicsContext gc) {
		super(t, gc);
		createSpeedSlider();
		createSpeedChooser();
		createPenSizeChooser();
		xy.play();
	}
	private static class Location {
		double x;
		double y;
	}
	private void createPenSizeChooser(){
		penSizeButton = new TextField();
		penSizeButton.setPromptText("Enter Pen Size");
		penSizeButton.setOnAction(e -> {
        	try{
        		penSize = Double.parseDouble(penSizeButton.getText());
        		penSizeButton.setText("");
        	}catch(IllegalArgumentException y){
        		
        	}
        	catch(NullPointerException i){}
        });
		extraButtons.add(penSizeButton);
		
	}
	private void createSpeedSlider() {
		speedLabel = buttonMaker.createLabel("Animation Speed : " + Integer.toString(DEFAULT_SPEED) + " milliseconds");
		slider = new Slider(DEFAULT_SPEED/30, 2*DEFAULT_SPEED, DEFAULT_SPEED);
		slider.setMajorTickUnit(DEFAULT_SPEED/3);
		slider.setShowTickMarks(true);
		slider.setSnapToTicks(true);
		extraButtons.add(speedLabel);
		extraButtons.add(slider);
	}
	private void createSpeedChooser() {
		slider.valueProperty().addListener((observable, oldValue, newValue) -> {
			slider.setValue(newValue.intValue());
			speedLabel.setText(String.format("Animation Speed : " + Integer.toString(newValue.intValue()) + " milliseconds"));
			speed = newValue.intValue();
		});
	}
	@Override
	public void moveTurtle(Trajectory T,double screenWidth, double screenHeight){
		SequentialTransition x = new SequentialTransition();
		for (UnmodifiableTurtleComposite uts : T){
			if (!skipFirst){
				currentXPos=myTurtleView.getImage().getX()+myTurtleView.getImage().getTranslateX()+myTurtleView.getImage().getBoundsInLocal().getWidth()/2;
				currentYPos=myTurtleView.getImage().getY()+myTurtleView.getImage().getTranslateY()+myTurtleView.getImage().getBoundsInLocal().getHeight()/2;
			}
			if (skipFirst){
				PathTransition pt = moveLocations(uts, screenWidth, screenHeight,currentXPos,currentYPos);
				RotateTransition rt = rotates(uts,screenWidth);
				FadeTransition ft = changeVisibilitys(uts,screenWidth);
				if (pt!=null){
					x.getChildren().add(pt);
				}
				if (rt!=null){
					x.getChildren().add(rt);
				}
				if (ft!=null){
					x.getChildren().add(ft);
				}
			}
			skipFirst=true;
		}
		x.setOnFinished(e->{xy.getChildren().remove(x); xy.play();});
		xy.getChildren().add(x);
		System.out.println(x);
		xy.play();
	}
	@Override
	protected void draw(UnmodifiableTurtleComposite uts, double screenWidth, double screenHeight) {
		//no function because drawing is simultaneous with movement
	}
	@Override
	protected void moveLocation(UnmodifiableTurtleComposite uts, double screenWidth, double screenHeight){

	}
	@Override
	protected void rotate(UnmodifiableTurtleComposite uts){

	}
	@Override
	protected void changeVisibility(UnmodifiableTurtleComposite uts){

	}
	protected PathTransition moveLocations(UnmodifiableTurtleComposite uts, double screenWidth, double screenHeight, double X, double Y) {
		
		double penX=uts.getX()+screenWidth/2;
		double penY=-uts.getY()+screenHeight/2;
		
		myTurtleView.setPen(uts.isPenDown());
		if (currentXPos!=penX || currentYPos!=penY){
		Path path = new Path();
		path.getElements().addAll(new MoveTo(currentXPos,currentYPos), new LineTo(penX,penY));
		PathTransition pt = new PathTransition(Duration.millis(speed), path, myTurtleView.getImage());
		if (myTurtleView.getPen()){
			pt.currentTimeProperty().addListener( new ChangeListener<Duration>() {
				Location oldLocation = null;

				/**
				 * Draw a line from the old location to the new location
				 */
				@Override
				public void changed(ObservableValue<? extends Duration> observable, Duration oldValue, Duration newValue) {
					ImageView pen = myTurtleView.getImage(); 
					// get current location
					double x = myTurtleView.getImage().getX()+myTurtleView.getImage().getTranslateX()+myTurtleView.getImage().getBoundsInLocal().getWidth()/2;
					double y =myTurtleView.getImage().getY()+myTurtleView.getImage().getTranslateY()+myTurtleView.getImage().getBoundsInLocal().getHeight()/2;
					//				System.out.println(x);

					// initialize the location
					if( oldLocation == null) {
						oldLocation = new Location();
						oldLocation.x = x;
						oldLocation.y = y;
						return;
					}

					// draw line
					graphics.setStroke(myTurtleView.getPenColor());
					graphics.setLineWidth(penSize);
					graphics.strokeLine(oldLocation.x, oldLocation.y, x, y);

					// update old location with current one
					oldLocation.x = x;
					oldLocation.y = y;
				}
			});
		}
		currentXPos=penX;
		currentYPos=penY;
		return pt;
		}
		return null;

	}				            


	protected RotateTransition rotates(UnmodifiableTurtleComposite uts, double width) {
		
		if (uts.getHeading()!=currentRotate){
			RotateTransition rt = new RotateTransition(Duration.millis(speed),myTurtleView.getImage());
			rt.setFromAngle(currentRotate);
			rt.setToAngle(uts.getHeading());
			currentRotate=uts.getHeading();
			return rt;
		}
		return null;
	}

	protected FadeTransition changeVisibilitys(UnmodifiableTurtleComposite uts,double width) {
		double newOpacity=0;
		if (uts.isShowing()){
			newOpacity=1.0;
		}
		if (currentOpacity!=newOpacity){
			FadeTransition ft1 = new FadeTransition(Duration.millis(speed), myTurtleView.getImage());
			ft1.setToValue(newOpacity);
			currentOpacity=newOpacity;
			return ft1;
		}
		return null;
	}
}
