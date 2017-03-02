package GUI;

import javafx.scene.control.ComboBox;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class TurtleComboBox {
	private ComboBox<ImageView> turtleChoice;
	public TurtleComboBox(TurtleViewManager t){
		turtleChoice = new ComboBox<ImageView>();
		turtleChoice.getItems().add(new ImageView(new Image(getClass().getClassLoader().getResourceAsStream("turtle.gif"))));
		turtleChoice.getItems().add(new ImageView(new Image(getClass().getClassLoader().getResourceAsStream("turtle2.gif"))));
		turtleChoice.valueProperty().addListener((x, y, newValue) -> {
			t.getImage().setImage(newValue.getImage());
		});
}
	public ComboBox<ImageView> getTurtleChooser(){
		return turtleChoice;
	}
}
