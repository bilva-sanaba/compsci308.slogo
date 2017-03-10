package GUI;


import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;

import GUI_TurtleMovers.TurtleViewManager;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.util.Callback;
import javafx.scene.image.Image;
public class TurtleComboBox {
	private TextAreaWriter myTextArea;
	private Language myLanguage;
	private ComboBox<String> turtleChoice;
	private ResourceBundle myResources;
	private Button runButton;
	public static final String DEFAULT_RESOURCE_BUNDLE="resources.languages/";
	public TurtleComboBox(TurtleViewManager tvm,TextAreaWriter t,Language l,Button rb){
		myTextArea=t;
		myLanguage=l;
		runButton=rb;
		turtleChoice = new ComboBox<String>();
		turtleChoice.getItems().add("turtle.gif");
		turtleChoice.getItems().add("turtle2.gif");
		turtleChoice.getItems().add("Turtle.png");
		turtleChoice.setPromptText("Choose Turtle");
		turtleChoice.setCellFactory(c-> new TurtleListCell());
		turtleChoice.setButtonCell(new TurtleListCell());
	turtleChoice.valueProperty().addListener((x, y, newValue) -> {
		changeImage(tvm,newValue);
		});
		
	}
	public ComboBox<String> getTurtleChooser(){
		return turtleChoice;
	}
	private void changeImage(TurtleViewManager tvm, String newString){
		myTextArea.setText(getText(newString));
		runButton.fire();
		ImageView newValue=new ImageView(new Image(getClass().getClassLoader().getResourceAsStream(newString)));
		tvm.getImage().setX(tvm.getImage().getX()+tvm.getImage().getBoundsInLocal().getWidth()/2);
		tvm.getImage().setY(tvm.getImage().getY()+tvm.getImage().getBoundsInLocal().getHeight()/2);
		tvm.getImage().setImage(newValue.getImage());
		tvm.getImage().setY(tvm.getImage().getY()-tvm.getImage().getBoundsInLocal().getHeight()/2);
		tvm.getImage().setX(tvm.getImage().getX()-tvm.getImage().getBoundsInLocal().getWidth()/2);
	}
private String getText(String newString){
	myResources=ResourceBundle.getBundle(DEFAULT_RESOURCE_BUNDLE+myLanguage.getLanguage());
	
	String command=(myResources.getString("SetShape").split("\\|")[0]);
	command+=" ";
	command+=Integer.toString(turtleChoice.getSelectionModel().getSelectedIndex()+1);
	return command;
}
}
