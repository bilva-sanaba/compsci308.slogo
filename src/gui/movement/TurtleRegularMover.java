/**
 * same as animated class but with flashing movement
 */
package gui.movement;

import gui.executables.boxes.Palette;
import javafx.geometry.Rectangle2D;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.transform.Rotate;
import model.configuration.SingleTurtleState;
import model.configuration.Trajectory;
import model.configuration.UnmodifiableTurtleComposite;
/**
 * Subclass of TurtleViewManager which updates a turtle by moving with flashing rather than animation
 * @author Bilva
 *
 */
public class TurtleRegularMover extends TurtleViewManager{
	public static final int DEFAULT_FPS = 10;
	public static final double MILLIS_PER_SECOND = 1000;

	public TurtleRegularMover(TurtleView t,GraphicsContext gc,Palette p){
		super(t,gc,p);
		extraButtonCount=0;
	}

	protected void moveLocation(SingleTurtleState uts,double screenWidth, double screenHeight){
		double newX=uts.getX()+screenWidth/2;
		double newY=-uts.getY()+screenHeight/2;
		setX(newX);
		setY(newY);
	}	

	protected void draw(SingleTurtleState uts,double screenWidth, double screenHeight){
		shouldDraw= uts.isPenDown();
		if (shouldDraw){
			double oldX=getX()+getWidth()/2;
			double oldY=getY()+getHeight()/2;
			double penX=uts.getX()+screenWidth/2;
			double penY=-uts.getY()+screenHeight/2;
			graphics.setStroke(getPenColor(uts));
			graphics.setLineWidth(uts.getPenSize());
			graphics.strokeLine(oldX, oldY, penX, penY);
		}
	}
	protected void rotate(SingleTurtleState uts){
		double newHeading=uts.getHeading();
		myTurtleView.getImage().setRotate(newHeading);
	}
	protected void changeVisibility(SingleTurtleState uts){
		setVisibility(uts.isShowing());
	}
}
