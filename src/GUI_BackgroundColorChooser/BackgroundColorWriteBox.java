package GUI_BackgroundColorChooser;

import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;

public class BackgroundColorWriteBox extends BackgroundColorComboBox {
	private Paint p;
	public BackgroundColorWriteBox(Shape rect){
		super(rect);
		doStuff();
	}
	private void doStuff(){
	SimpleObjectProperty<ObservableList<String>> words = 
            new SimpleObjectProperty<>(FXCollections.observableArrayList());
        colorPicker = new ComboBox<String>();
        ((ComboBox<String>) colorPicker).itemsProperty().bind(words);
        TextField inputText = new TextField();
        inputText.setOnAction(e -> {
        	try{
        		Color.web(inputText.getText());
        		words.getValue().add(inputText.getText());
        		 inputText.setText("");
        	}
        	catch(IllegalArgumentException y){
        		
        	}
        	catch(NullPointerException i){}
        });
        ((ComboBox<String>)colorPicker).valueProperty().addListener((x, y, newValue) -> {
			p=Color.web(newValue);	
		});
	}
	@Override
	protected Paint generateColor() {
		
		return p;
	}

}
