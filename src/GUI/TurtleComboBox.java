package GUI;

import javafx.scene.control.ComboBox;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class TurtleComboBox {
	private ComboBox<ImageView> turtleChoice;
	public TurtleComboBox(TurtleViewManager tvm){
		turtleChoice = new ComboBox<ImageView>();
		turtleChoice.getItems().add(new ImageView(new Image(getClass().getClassLoader().getResourceAsStream("turtle.gif"))));
		turtleChoice.getItems().add(new ImageView(new Image(getClass().getClassLoader().getResourceAsStream("turtle2.gif"))));
		turtleChoice.setPromptText("Choose Turtle");
		turtleChoice.valueProperty().addListener((x, y, newValue) -> {
			tvm.getImage().setImage(newValue.getImage());
			ComboBox<ImageView>ne=new ComboBox<ImageView>();
			ne.getItems().addAll(turtleChoice.getItems());
			turtleChoice.getItems().clear();
			turtleChoice.getItems().addAll(ne.getItems());
		});
}
	public ComboBox<ImageView> getTurtleChooser(){
		return turtleChoice;
	}
}
