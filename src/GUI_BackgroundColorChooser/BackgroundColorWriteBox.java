package GUI_BackgroundColorChooser;

import GUI.Language;
import GUI.TextAreaWriter;
import GUI_Objects.Palette;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;

public class BackgroundColorWriteBox extends BackgroundColorComboBox {
	private Color p;
	private TextField inputText;
	public BackgroundColorWriteBox(TextAreaWriter t,Language l,Button runButton, Palette p){
		super(t,l,runButton,p);
		doStuff();
		displays.add(1,inputText);
	}
	private void doStuff(){
	SimpleObjectProperty<ObservableList<String>> words = 
        new SimpleObjectProperty<>(FXCollections.observableArrayList());
        ((ComboBox<String>) colorPicker).itemsProperty().bind(words);
        ((ComboBox<String>) colorPicker).setPromptText("Select Background Color");
        inputText = new TextField();
        inputText.setPromptText("Enter Background Color");
        inputText.setOnAction(e -> {
        	try{
        		p = Color.web(inputText.getText());
        		setColor();
        		((ComboBox<String>) colorPicker).getOnAction();
        		words.getValue().add(inputText.getText());
        		inputText.setText("");
        	}
        	catch(IllegalArgumentException y){
        		
        	}
        	catch(NullPointerException i){}
        });
   
        ((ComboBox<String>)colorPicker).valueProperty().addListener((x, y, newValue) -> {
			p=Color.web(newValue);	
			((ComboBox<String>) colorPicker).getOnAction();
		});
	}
	@Override
	protected Color generateColor() {
		return p;
	}

}
