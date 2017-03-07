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


	private ComboBox<ImageView> turtleChoice;
	public TurtleComboBox(TurtleViewManager tvm){
		turtleChoice = new ComboBox<ImageView>();
		turtleChoice.getItems().add(new ImageView(new Image(getClass().getClassLoader().getResourceAsStream("turtle.gif"))));
		turtleChoice.getItems().add(new ImageView(new Image(getClass().getClassLoader().getResourceAsStream("turtle2.gif"))));
		turtleChoice.getItems().add(new ImageView(new Image(getClass().getClassLoader().getResourceAsStream("Turtle.png"))));
		turtleChoice.setPromptText("Choose Turtle");
		turtleChoice.valueProperty().addListener((x, y, newValue) -> {
			changeImage(tvm,newValue);
			
			
			ComboBox<ImageView>ne=new ComboBox<ImageView>();
			ne.getItems().addAll(turtleChoice.getItems());
			turtleChoice.getItems().clear();
			turtleChoice.getItems().addAll(ne.getItems());
		});
		//turtleChoice.setCellFactory(new Callback<ListView<Image>,ListCell<Image>>);
	}
	public ComboBox<ImageView> getTurtleChooser(){
		return turtleChoice;
	}
	private void changeImage(TurtleViewManager tvm, ImageView newValue){
		tvm.getImage().setX(tvm.getImage().getX()+tvm.getImage().getBoundsInLocal().getWidth()/2);
		tvm.getImage().setY(tvm.getImage().getY()+tvm.getImage().getBoundsInLocal().getHeight()/2);
		tvm.getImage().setImage(newValue.getImage());
		tvm.getImage().setY(tvm.getImage().getY()-tvm.getImage().getBoundsInLocal().getHeight());
		tvm.getImage().setX(tvm.getImage().getX()-tvm.getImage().getBoundsInLocal().getWidth());
		System.out.println(tvm.getImage().getBoundsInLocal().getHeight());
		
	}
private void refreshBox(TurtleViewManager t,Image newValue){
	t.getImage().setImage(newValue);
	/*ComboBox<Image>ne=new ComboBox<Image>();
	ne.getItems().addAll(turtleChoice.getItems());
	turtleChoice.getItems().clear();
	
	turtleChoice.getItems().addAll(ne.getItems());
*/
}
}
