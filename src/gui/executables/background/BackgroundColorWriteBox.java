package gui.executables.background;
import java.util.ArrayList;
import java.util.Collection;
import gui.executables.FireableButton;
import gui.executables.TextAreaWriter;
import gui.executables.boxes.Palette;
import gui.language.Language;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
/**
 *  Subclass of BackgroundColorChooser which implements it as Textbox for color entry
 *  as well as a combobox which displays enter colors
 * @author Bilva
 */
public class BackgroundColorWriteBox extends BackgroundColorComboBox {
	private Color currentColor;
	private TextField inputText;
	private Collection<String> usedColor = new ArrayList<String>();
	private SimpleObjectProperty<ObservableList<String>> words;
	/**
	 * Creates textfield and combobox with binding
	 * Both objects change the currentColor which is what is sent for display
	 * @param rb Fireable runbutton
	 * @param t Encapsulating object of command textArea
	 * @param l Encapsulation of current language in workspace
	 * @param p Object storing data structure with current color palette representation
	 */
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
        inputText.setPromptText(myButtonResources.getString("BackgroundTextField"));
        inputText.setOnAction(e -> {
        	try{
        		currentColor = Color.web(inputText.getText());
        		executeCommand();
        		((ComboBox<String>) colorPicker).getOnAction();
        		if (!usedColor.contains(inputText.getText())){
        			words.getValue().add(inputText.getText());
        			usedColor.add(inputText.getText());
        		}
        		inputText.setText("");
        	}
        	catch(IllegalArgumentException y){}
        	catch(NullPointerException i){}
        });
	}
	private void createComboBox(){
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
