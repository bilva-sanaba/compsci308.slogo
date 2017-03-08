package GUI_TurtleMovers;
import configuration.Trajectory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;

public class TurtleView {
	private ImageView Turtle_Image;
	private boolean penDown;
	private boolean visible;
	private Paint penColor; 
	public TurtleView(){
		Image image = new Image(getClass().getClassLoader().getResourceAsStream("turtle2.gif"));
		Turtle_Image = new ImageView(image);
		penDown=true;
		visible=true;
		penColor=Color.BLACK;
	}
	public Paint getPenColor(){
		return penColor;
	}
	public ImageView getImage(){
		return Turtle_Image;
	}
	public void setX(double xCoor){
		Turtle_Image.setX(xCoor);
	}
	public void setY(double yCoor){
		Turtle_Image.setY(yCoor);
	}
	public void setHeading(double heading){
		Turtle_Image.setRotate(heading);
	}
	public void setPen(boolean x){
		penDown=x;
	}
	public void setPenUp(){
		penDown=false;
	}
	public boolean getPen(){
		return penDown;
	}
	public void setVisibility(boolean v){
		Turtle_Image.setVisible(v);
	}

	public void move(Trajectory T){
	}
	public void setPenColor(Paint p){
		penColor=p;
	}
}
