package gui.movement;
import javafx.beans.property.ReadOnlyStringProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ObservableStringValue;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import model.configuration.Trajectory;
/**
 * Class which encapsulates the image of the turtle 
 * (currently has a get image method but all uses should be changed to using another public method)
 * @author Bilva
 *
 */
public class TurtleView {
	private ImageView Turtle_Image;
	private boolean penDown;
	private Paint penColor; 
	private StringProperty turtleString;
	public TurtleView(String s,String c){
		Image image = new Image(getClass().getClassLoader().getResourceAsStream(s));
		Turtle_Image = new ImageView(image);
		penDown=true;
		penColor=Color.valueOf(c);
		turtleString = new SimpleStringProperty(s);
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
	
	public void setShape(String s){
		turtleString.set(s);
		ImageView newValue=new ImageView(new Image(getClass().getClassLoader().getResourceAsStream(s)));
		setX(getImage().getX()+getImage().getBoundsInLocal().getWidth()/2);
		setY(getImage().getY()+getImage().getBoundsInLocal().getHeight()/2);
		Turtle_Image.setImage(newValue.getImage());
		setY(getImage().getY()-getImage().getBoundsInLocal().getHeight()/2);
		setX(getImage().getX()-getImage().getBoundsInLocal().getWidth()/2);
	}
	public boolean getPen(){
		return penDown;
	}
	public void setVisibility(boolean v){
		Turtle_Image.setVisible(v);
	}
	public ReadOnlyStringProperty getTurtleString(){
		return turtleString;
	}
}
