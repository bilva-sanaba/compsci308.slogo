package GUI;


import java.util.Arrays;
import java.util.List;

import GUI_TurtleMovers.TurtleViewManager;

import javafx.scene.control.ComboBox;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Callback;
import javafx.scene.image.Image;
public class TurtleComboBox {


	private ComboBox<String> turtleChoice;
	public TurtleComboBox(TurtleViewManager tvm){
		turtleChoice = new ComboBox<String>();
		turtleChoice.getItems().add("turtle.gif");
		turtleChoice.getItems().add("turtle2.gif");
		turtleChoice.getItems().add("Turtle.png");
		turtleChoice.setPromptText("Choose Turtle");
		turtleChoice.setCellFactory(c-> new TurtleCellFactory());
		turtleChoice.setButtonCell(new TurtleCellFactory());
	turtleChoice.valueProperty().addListener((x, y, newValue) -> {
		changeImage(tvm,newValue);
		});
		
	}
	public ComboBox<String> getTurtleChooser(){
		return turtleChoice;
	}
	private void changeImage(TurtleViewManager tvm, String newString){
		ImageView newValue=new ImageView(new Image(getClass().getClassLoader().getResourceAsStream(newString)));
		System.out.println("ba"+tvm.getImage().getX()+tvm.getImage().getBoundsInLocal().getWidth()/2);
		tvm.getImage().setX(tvm.getImage().getX()+tvm.getImage().getBoundsInLocal().getWidth()/2);
		tvm.getImage().setY(tvm.getImage().getY()+tvm.getImage().getBoundsInLocal().getHeight()/2);
		tvm.getImage().setImage(newValue.getImage());
		System.out.println("b"+tvm.getImage().getX());
		System.out.println("c"+tvm.getImage().getX());
		tvm.getImage().setY(tvm.getImage().getY()-tvm.getImage().getBoundsInLocal().getHeight()/2);
		System.out.println("d"+tvm.getImage().getBoundsInLocal().getWidth());
		tvm.getImage().setX(tvm.getImage().getX()-tvm.getImage().getBoundsInLocal().getWidth()/2);
		System.out.println("e"+tvm.getImage().getX()+tvm.getImage().getBoundsInLocal().getWidth());
	}

}
