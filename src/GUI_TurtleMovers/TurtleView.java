package GUI_TurtleMovers;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import model.configuration.Trajectory;

public class TurtleView {
	private ImageView Turtle_Image;
	private boolean penDown;
	private boolean visible;
	private Paint penColor; 
	public TurtleView(String s,String c){
		Image image = new Image(getClass().getClassLoader().getResourceAsStream(s));
		Turtle_Image = new ImageView(image);
		penDown=true;
		visible=true;
		penColor=Color.valueOf(c);
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
	public void setShape(String s){
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

	public void move(Trajectory T){
	}
	public void setPenColor(Paint p){
		penColor=p;
	}
}
