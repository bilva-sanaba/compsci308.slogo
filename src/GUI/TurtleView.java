package GUI;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class TurtleView {
	private ImageView Turtle_Image;
	public TurtleView(){
		Image image = new Image(getClass().getClassLoader().getResourceAsStream("Turtle.gif"));
		Turtle_Image = new ImageView(image);
	}
	public ImageView getImage(){
		return Turtle_Image;
	}
}
