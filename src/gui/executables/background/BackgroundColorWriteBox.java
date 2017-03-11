package gui.executables.background;

import gui.executables.FireableButton;
import gui.executables.TextAreaWriter;
import gui.executables.boxes.Palette;
import gui.language.Language;
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
	private Color currentColor;
	private TextField inputText;
	private SimpleObjectProperty<ObservableList<String>> words;
	public BackgroundColorWriteBox(TextAreaWriter t,Language l,FireableButton runButton, Palette p){
		super(t,l,runButton,p);
		initializeButtons();
		displays.add(1,inputText);
	}
	private void initializeButtons(){
		createBinding();
		createTextField();
		createComboBox();
	}
	private void createBinding(){
		words = new SimpleObjectProperty<>(FXCollections.observableArrayList());
        ((ComboBox<String>) colorPicker).itemsProperty().bind(words);
	}
	private void createTextField(){
		inputText = new TextField();
        inputText.setPromptText("Enter Background Color");
        inputText.setOnAction(e -> {
        	try{
        		currentColor = Color.web(inputText.getText());
        		activate();
        		((ComboBox<String>) colorPicker).getOnAction();
        		words.getValue().add(inputText.getText());
        		inputText.setText("");
        	}
        	catch(IllegalArgumentException y){
        		
        	}
        	catch(NullPointerException i){}
        });
	}
	private void createComboBox(){
        ((ComboBox<String>) colorPicker).setPromptText("Select Background Color");  
        ((ComboBox<String>)colorPicker).valueProperty().addListener((x, y, newValue) -> {
			currentColor=Color.web(newValue);	
			((ComboBox<String>) colorPicker).getOnAction();
		});
	}
	
	@Override
	protected Color generateColor() {
		return currentColor;
	}

}
