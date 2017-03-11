package GUI_TurtleMovers;

import java.util.ArrayList;
import java.util.List;

import GUI.GUI;
import GUI.TurtleComboBox;
import GUI_Objects.ButtonMaker;
import GUI_Objects.Palette;
import model.configuration.CompositeTurtleState;
import model.configuration.SingleTurtleState;
import model.configuration.Trajectory;
import model.configuration.UnmodifiableTurtleComposite;
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
import model.configuration.Trajectory;
import model.configuration.UnmodifiableTurtleComposite;

public class TurtleAnimator extends TurtleViewManager{
	private ButtonMaker buttonMaker = new ButtonMaker();
	private static final double DEFAULT_PEN_SIZE = 4;
	private TextField penSizeButton;
	private double currentXPos;
	private double currentYPos;
	private double currentRotate=0;
	private double currentOpacity=1.0;
	private boolean skipFirst = false;
	private SpeedSlider mySlider= new SpeedSlider();
	public TurtleAnimator(TurtleView t, GraphicsContext gc,Palette p) {
		super(t, gc,p);
		penSize = DEFAULT_PEN_SIZE;
		extraButtons = mySlider.getButtons();
	}


	@Override
	public void moveTurtle(SingleTurtleTrajectory T,double screenWidth, double screenHeight){
		SequentialTransition x = new SequentialTransition();
		for (SingleTurtleState uts : T){
			this.setShape(uts);
			if (!skipFirst){
				currentXPos=myTurtleView.getImage().getX()+myTurtleView.getImage().getTranslateX()+myTurtleView.getImage().getBoundsInLocal().getWidth()/2;
				currentYPos=myTurtleView.getImage().getY()+myTurtleView.getImage().getTranslateY()+myTurtleView.getImage().getBoundsInLocal().getHeight()/2;
			}
			if (skipFirst){
				PathTransition pt = moveLocations(uts, screenWidth, screenHeight,currentXPos,currentYPos);
				RotateTransition rt = rotates(uts);
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
		x.play();
	}
	@Override
	protected void draw(SingleTurtleState uts, double screenWidth, double screenHeight) {
		//no function because drawing is simultaneous with movement
	}
	@Override
	protected void moveLocation(SingleTurtleState uts, double screenWidth, double screenHeight){

	}
	@Override
	protected void rotate(SingleTurtleState uts){

	}
	@Override
	protected void changeVisibility(SingleTurtleState uts){

	}
	protected PathTransition moveLocations(SingleTurtleState uts, double screenWidth, double screenHeight, double X, double Y) {
		
		double penX=uts.getX()+GUI.BACKGROUND_WIDTH/2;
		double penY=-uts.getY()+GUI.BACKGROUND_HEIGHT/2;
		
		myTurtleView.setPen(uts.isPenDown());
		if (currentXPos!=penX || currentYPos!=penY){
		Path path = new Path();
		path.getElements().addAll(new MoveTo(currentXPos,currentYPos), new LineTo(penX,penY));
		PathTransition pt = new PathTransition(Duration.millis(mySlider.getSpeed()), path, myTurtleView.getImage());
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
					graphics.setStroke(getPenColor(uts));
					graphics.setLineWidth(uts.getPenSize());
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


	protected RotateTransition rotates(SingleTurtleState uts) {
		
		if (uts.getHeading()!=currentRotate){
			RotateTransition rt = new RotateTransition(Duration.millis(mySlider.getSpeed()),myTurtleView.getImage());
			rt.setFromAngle(currentRotate);
			rt.setToAngle(uts.getHeading());
			currentRotate=uts.getHeading();
			return rt;
		}
		return null;
	}

	protected FadeTransition changeVisibilitys(SingleTurtleState uts,double width) {
		double newOpacity=0;
		if (uts.isShowing()){
			newOpacity=1.0;
		}
		if (currentOpacity!=newOpacity){
			FadeTransition ft1 = new FadeTransition(Duration.millis(mySlider.getSpeed()), myTurtleView.getImage());
			ft1.setToValue(newOpacity);
			currentOpacity=newOpacity;
			return ft1;
		}
		return null;
	}
}
