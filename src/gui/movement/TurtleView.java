package gui.movement;
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
	private Color defaultPenColor; 
	public TurtleView(String s,String c){
		Image image = new Image(getClass().getClassLoader().getResourceAsStream(s));
		Turtle_Image = new ImageView(image);
		defaultPenColor=Color.valueOf(c);
	}

	public ImageView getImage(){
		return Turtle_Image;
	}
	public Color getDefaultPenColor(){
		return defaultPenColor;
	}

	public void setShape(String s){
		ImageView newValue=new ImageView(new Image(getClass().getClassLoader().getResourceAsStream(s)));
		Turtle_Image.setX(getImage().getX()+getImage().getBoundsInLocal().getWidth()/2);
		Turtle_Image.setY(getImage().getY()+getImage().getBoundsInLocal().getHeight()/2);
		Turtle_Image.setImage(newValue.getImage());
		Turtle_Image.setY(getImage().getY()-getImage().getBoundsInLocal().getHeight()/2);
		Turtle_Image.setX(getImage().getX()-getImage().getBoundsInLocal().getWidth()/2);
	}
}
