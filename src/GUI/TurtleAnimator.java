package GUI;

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
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.shape.LineTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import javafx.scene.transform.Rotate;
import javafx.util.Duration;

public class TurtleAnimator extends TurtleViewManager{
	public TurtleAnimator(TurtleView t, GraphicsContext gc) {
		super(t, gc);
	}
public static class Location {
    double x;
    double y;
}

@Override
protected void draw(UnmodifiableTurtleState uts, double screenWidth, double screenHeight) {
	//no function because drawing is simultaneous with movement
	}
@Override
protected void moveLocation(UnmodifiableTurtleState uts, double screenWidth, double screenHeight) {
	double oldX=myTurtleView.getImage().getX()+myTurtleView.getImage().getBoundsInLocal().getWidth()/2;
	double oldY=myTurtleView.getImage().getY()+myTurtleView.getImage().getBoundsInLocal().getHeight()/2;
	double penX=uts.getX()+screenWidth/2;
	double penY=-uts.getY()+screenHeight/2;
	double newX=uts.getX()+screenWidth/2-myTurtleView.getImage().getBoundsInLocal().getWidth()/2;
	double newY=-uts.getY()+screenHeight/2-myTurtleView.getImage().getBoundsInLocal().getHeight()/2;
	boolean newPen = uts.isPenDown();
	if (oldX!=penX || oldY!=penY){
		Path path = new Path();
		
		path.getElements().addAll(new MoveTo(oldX,oldY), new LineTo(penX,penY));
		PathTransition pt = new PathTransition(Duration.millis(4000), path, myTurtleView.getImage());
		pt.currentTimeProperty().addListener( new ChangeListener<Duration>() {
			Location oldLocation = null;

	            /**
	             * Draw a line from the old location to the new location
	             */
	            @Override
	            public void changed(ObservableValue<? extends Duration> observable, Duration oldValue, Duration newValue) {
	                ImageView pen = myTurtleView.getImage(); 
	                // get current location
	                double x = pen.getTranslateX()+penX;
	                double y = pen.getTranslateY()+penY+myTurtleView.getImage().getBoundsInParent().getHeight();

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
		pt.play();
	}
}				            

@Override
protected void rotate(UnmodifiableTurtleState uts) {
	if (uts.getHeading()!=myTurtleView.getImage().getRotate()){
		System.out.println(myTurtleView.getImage().getRotate());
		RotateTransition rt = new RotateTransition(Duration.seconds(3),myTurtleView.getImage());
		rt.setFromAngle(myTurtleView.getImage().getRotate());
		rt.setToAngle(uts.getHeading());
		rt.play();
	}
}
@Override
protected void changeVisibility(UnmodifiableTurtleState uts) {
	 FadeTransition ft1 = new FadeTransition(Duration.millis(2000), myTurtleView.getImage());
	 if (uts.isShowing()){
		 ft1.setToValue(1.0);
	 } else{
		 ft1.setToValue(0.0);
	 }
	 ft1.play();
}
}
