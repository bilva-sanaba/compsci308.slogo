package GUI_TurtleMovers;

import GUI.GUI;

import configuration.Trajectory;
import configuration.UnmodifiableTurtleState;
import javafx.animation.FadeTransition;
import javafx.animation.PathTransition;
import javafx.animation.RotateTransition;
import javafx.animation.SequentialTransition;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.beans.value.ObservableValueBase;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.shape.LineTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import javafx.scene.transform.Rotate;
import javafx.util.Duration;

public class TurtleAnimator extends TurtleViewManager{
	private static final int DEFAULT_SPEED = 4000;
	private int speed=DEFAULT_SPEED;
	private Slider slider;
	private Label speedLabel;
	public TurtleAnimator(TurtleView t, GraphicsContext gc,double w) {
		super(t, gc,w);
		createSpeedSlider();
		createSpeedChooser();
	}
	public static class Location {
		double x;
		double y;
	}
	private void createSpeedSlider() {
		speedLabel = GUI.createLabel("Animation Speed : " + Integer.toString(DEFAULT_SPEED) + " milliseconds");
		slider = new Slider(1, 8000, 4000);
		slider.setMajorTickUnit(1000);
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
//@Override
//public void moveTurtle(Trajectory T,double screenWidth, double screenHeight){
//	
//}
@Override
protected void draw(UnmodifiableTurtleState uts, double screenWidth, double screenHeight) {
	//no function because drawing is simultaneous with movement
}
@Override
protected void moveLocation(UnmodifiableTurtleState uts, double screenWidth, double screenHeight) {
	double oldX=myTurtleView.getImage().getX()+myTurtleView.getImage().getTranslateX()+myTurtleView.getImage().getBoundsInLocal().getWidth()/2;
	double oldY=myTurtleView.getImage().getY()+myTurtleView.getImage().getTranslateY()+myTurtleView.getImage().getBoundsInLocal().getHeight()/2;
	System.out.println(oldX);
	System.out.println(oldY);
	double penX=uts.getX()+screenWidth/2;
	double penY=-uts.getY()+screenHeight/2;
	double newX=uts.getX()+screenWidth/2-myTurtleView.getImage().getBoundsInLocal().getWidth()/2;
	double newY=-uts.getY()+screenHeight/2-myTurtleView.getImage().getBoundsInLocal().getHeight()/2;
	boolean newPen = uts.isPenDown();
	if (oldX!=penX || oldY!=penY){
		Path path = new Path();
		path.getElements().addAll(new MoveTo(oldX,oldY), new LineTo(penX,penY));
		PathTransition pt = new PathTransition(Duration.millis(speed), path, myTurtleView.getImage());
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
				graphics.setLineWidth(4);
				graphics.strokeLine(oldLocation.x, oldLocation.y, x, y);

				// update old location with current one
				oldLocation.x = x;
				oldLocation.y = y;
			}
		});
		pt.setOnFinished(e -> rotate(uts,width));
		pt.play();
	}
}				            

@Override
protected void rotate(UnmodifiableTurtleState uts, double width) {
	if (uts.getHeading()!=myTurtleView.getImage().getRotate()){
		RotateTransition rt = new RotateTransition(Duration.millis(speed),myTurtleView.getImage());
		rt.setFromAngle(myTurtleView.getImage().getRotate());
		rt.setToAngle(uts.getHeading());
		rt.setOnFinished(e-> changeVisibility(uts,width));
		rt.play();
	}
}
@Override
protected void changeVisibility(UnmodifiableTurtleState uts,double width) {
	FadeTransition ft1 = new FadeTransition(Duration.millis(speed), myTurtleView.getImage());
	if (uts.isShowing()){
		ft1.setToValue(1.0);
	} else{
		ft1.setToValue(0.0);
	}
	ft1.play();
}
}
