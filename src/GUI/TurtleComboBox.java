package GUI;

import java.util.Arrays;
import java.util.List;

import javafx.scene.control.ComboBox;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.util.Callback;
import javafx.scene.image.Image;
public class TurtleComboBox {
	public static final List<String>turtles=Arrays.asList(new String[]{"turtle.gif","turtle2.gif"});
	private ComboBox<Image> turtleChoice;
	public TurtleComboBox(TurtleViewManager t){
		initializeComboBox(t);
}
	private void initializeComboBox(TurtleViewManager t){
		turtleChoice = new ComboBox<Image>();
		for(String turtle:turtles)
		turtleChoice.getItems().add(new Image(getClass().getClassLoader().getResourceAsStream(turtle)));
		turtleChoice.setPromptText("Choose Turtle");
		turtleChoice.valueProperty().addListener((x, y, newValue) -> {
			
				refreshBox(t,newValue);
			
			
		});
		//turtleChoice.setCellFactory(new Callback<ListView<Image>,ListCell<Image>>);
	}
	public ComboBox<Image> getTurtleChooser(){
		return turtleChoice;
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
