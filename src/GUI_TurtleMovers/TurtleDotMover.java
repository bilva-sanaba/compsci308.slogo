package GUI_TurtleMovers;

import configuration.UnmodifiableTurtleState;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.TextField;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Shape;

public class TurtleDotMover extends TurtleViewManager {
	  public static final int DEFAULT_FPS = 10;
	    public static final double MILLIS_PER_SECOND = 1000;
		private double penSize = DEFAULT_PEN_SIZE;
		private TextField penSizeButton;
		private static final double DEFAULT_PEN_SIZE = 4;
		private Shape stamp;
	    
		public TurtleDotMover(TurtleView t,GraphicsContext gc){
			super(t,gc);
			createPenSizeChooser();
			 createStamp();
		}
	protected void moveLocation(UnmodifiableTurtleState uts,double screenWidth, double screenHeight){
		double newX=uts.getX()+screenWidth/2-myTurtleView.getImage().getBoundsInLocal().getWidth()/2;
		double newY=-uts.getY()+screenHeight/2-myTurtleView.getImage().getBoundsInLocal().getHeight()/2;
		myTurtleView.setX(newX);
		myTurtleView.setY(newY);
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

	protected void draw(UnmodifiableTurtleState uts,double screenWidth, double screenHeight){
		myTurtleView.setPen(uts.isPenDown());
		if (myTurtleView.getPen()){
			double oldX=myTurtleView.getImage().getX()+myTurtleView.getImage().getBoundsInLocal().getWidth()/2;
			double oldY=myTurtleView.getImage().getY()+myTurtleView.getImage().getBoundsInLocal().getHeight()/2;
			double penX=uts.getX()+screenWidth/2;
			double penY=-uts.getY()+screenHeight/2;
			graphics.setStroke(myTurtleView.getPenColor());
			graphics.setFill(myTurtleView.getPenColor());
			graphics.fillOval(oldX, oldY, penSize, penSize);
		}
	}
	private void createStamp(){
		stamp = new Circle(penSize,myTurtleView.getPenColor());
	}
	protected void rotate(UnmodifiableTurtleState uts){
		double newHeading=uts.getHeading();
		myTurtleView.getImage().setRotate(newHeading);
	}
	protected void changeVisibility(UnmodifiableTurtleState uts){
		myTurtleView.setVisibility(uts.isShowing());
	}
}
