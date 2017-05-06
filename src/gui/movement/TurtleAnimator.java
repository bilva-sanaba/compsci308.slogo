/**
 * Class which takes a turtle trajectory and moves an image on screen
 */
package gui.movement;

import java.util.ArrayList;
import java.util.List;

import gui.ButtonMaker;
import gui.GUI;
import gui.executables.SpeedSlider;
import gui.executables.boxes.Palette;
import gui.executables.boxes.TurtleComboBox;
import gui.movement.utility.SingleTurtleTrajectory;
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
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.shape.LineTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import javafx.scene.transform.Rotate;
import javafx.util.Duration;
import model.configuration.Trajectory;
import model.configuration.UnmodifiableTurtleComposite;
/**
 * Subclass of TurtleView Manager, has methods to move the image of the turtle to appropriate locations with animation
 * when given a list of turtle states to iterate through 
 * @author Bilva
 *
 */
/**
 * SLogoAddition
 * @jwei528
 */
public class TurtleAnimator extends TurtleViewManager{
	private ButtonMaker buttonMaker = new ButtonMaker();
	private static final double DEFAULT_PEN_SIZE = 4;
	private static final double TURTLEWIDTH = 8;
	private static final double TURTLEHEIGHT=6;
	private TextField penSizeButton;
	private double currentXPos;
	private double currentYPos;
	private double currentRotate=0;
	private double currentOpacity=1.0;
	private int currentStampCount=0;
	private boolean clearStamp=false;
	private boolean skipFirst = false;
	private boolean addStamp=false;
	private SpeedSlider mySlider= new SpeedSlider();
	private List<ImageConfig> stampList;
	public TurtleAnimator(TurtleView t, GraphicsContext gc,Palette p) {
		super(t, gc,p);
		penSize = DEFAULT_PEN_SIZE;
		extraButtons = mySlider.getButtons();
		extraButtonCount=extraButtons.size();
		stampList = new ArrayList<ImageConfig>();
	}

	/**
	 * When called by the GUI this moves the turtle to appropriate locations
	 */
	@Override
	public void moveTurtle(SingleTurtleTrajectory T,double screenWidth, double screenHeight){
		SequentialTransition x = new SequentialTransition();
		for (SingleTurtleState uts : T){
			this.setShape(uts);
			stamp(T.getLast());
			if (!skipFirst){
				currentXPos=myTurtleView.getImageView().getX()+myTurtleView.getImageView().getTranslateX()+myTurtleView.getImageView().getBoundsInLocal().getWidth()/2;
				currentYPos=myTurtleView.getImageView().getY()+myTurtleView.getImageView().getTranslateY()+myTurtleView.getImageView().getBoundsInLocal().getHeight()/2;
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
	private PathTransition moveLocations(SingleTurtleState uts, double screenWidth, double screenHeight, double X, double Y) {

		double penX=uts.getX()+GUI.BACKGROUND_WIDTH/2;
		double penY=-uts.getY()+GUI.BACKGROUND_HEIGHT/2;
		
		
		myTurtleView.setPen(uts.isPenDown());
		if (currentXPos!=penX || currentYPos!=penY){
			if (shouldDraw){
				Path path = new Path();
				path.getElements().addAll(new MoveTo(currentXPos,currentYPos), new LineTo(penX,penY));
				PathTransition pt = new PathTransition(Duration.millis(mySlider.getSpeed()), path, myTurtleView.getImageView());
				if (myTurtleView.getPen()){

					//System.out.println(shouldDraw);
					pt.currentTimeProperty().addListener( new ChangeListener<Duration>() {
						Location oldLocation = null;

						/**
						 * Draw a line from the old location to the new location
						 */
						@Override
						public void changed(ObservableValue<? extends Duration> observable, Duration oldValue, Duration newValue) {
							ImageView pen = myTurtleView.getImageView(); 
							
							
							// get current location
							double x = myTurtleView.getImageView().getX()+myTurtleView.getImageView().getTranslateX()+myTurtleView.getImageView().getBoundsInLocal().getWidth()/2;
							double y =myTurtleView.getImageView().getY()+myTurtleView.getImageView().getTranslateY()+myTurtleView.getImageView().getBoundsInLocal().getHeight()/2;
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
						
							
							oldLocation.x = x;
							oldLocation.y = y;
							
							
						}
					});
				}
				currentXPos=penX;
				currentYPos=penY;
				return pt;
			}
		}
		return null;
	}	
	
	
	private void stamp(SingleTurtleState uts){
		//SLogoAddition
		Double turtleX= myTurtleView.getImageView().getX()+myTurtleView.getImageView().getTranslateX()+myTurtleView.getImageView().getBoundsInLocal().getWidth()/2;
		Double turtleY = myTurtleView.getImageView().getY()+myTurtleView.getImageView().getTranslateY()+myTurtleView.getImageView().getBoundsInLocal().getHeight()/2;
		Double x = turtleX-TURTLEWIDTH;
		Double y = turtleY-TURTLEHEIGHT;
		
		if(uts.getStampCount()>currentStampCount){
			currentStampCount++;
			addStamp=true;
		}
		
		if(uts.getStampCount()==0){
			clearStamp=true;
		}
		
		if(addStamp){
			graphics.save();
			Rotate r = new Rotate(currentRotate, x, y);
			graphics.setTransform(r.getMxx(), r.getMyx(), r.getMxy(), r.getMyy(), r.getTx(), r.getTy());
			
			Image iTurtle = myTurtleView.getImage();
			graphics.drawImage(iTurtle, x, y);
			addStamp=false;
			stampList.add(new ImageConfig(iTurtle, x, y));
			graphics.restore();
		}
		
		//clearstamps
		if(clearStamp && stampList.size()>0){
			for(ImageConfig i: stampList){
				Double iX = i.getImage().getHeight();
				Double iY = i.getImage().getWidth();
				Double iTopX = i.getX();
				Double iTopY = i.getY();
				graphics.clearRect(iTopX, iTopY, iX, iY);
			}
			stampList.clear();
			currentStampCount=0;
			clearStamp=false; 
		}
	}


	private RotateTransition rotates(SingleTurtleState uts) {

		if (uts.getHeading()!=currentRotate){
			RotateTransition rt = new RotateTransition(Duration.millis(mySlider.getSpeed()),myTurtleView.getImageView());
			rt.setFromAngle(currentRotate);
			rt.setToAngle(uts.getHeading());
			currentRotate=uts.getHeading();
			return rt;
		}
		return null;
	}

	private FadeTransition changeVisibilitys(SingleTurtleState uts,double width) {
		double newOpacity=0;
		if (uts.isShowing()){
			newOpacity=1.0;
		}
		if (currentOpacity!=newOpacity){
			FadeTransition ft1 = new FadeTransition(Duration.millis(mySlider.getSpeed()), myTurtleView.getImageView());
			ft1.setToValue(newOpacity);
			currentOpacity=newOpacity;
			return ft1;
		}
		return null;
	}
}
