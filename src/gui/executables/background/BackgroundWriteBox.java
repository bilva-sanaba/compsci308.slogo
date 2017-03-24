// This entire file is part of my masterpiece.
// Bilva Sanaba
// This is a concrete example of a BackgroundComboBox
// In this example it is implemented as a textfield and combobox. The textfield is activated when enter is pressed. If 
//     a valid color is entered, it will add the color to the combobox if it is not already in the combobox. It will then 
//     set the background color to the typed in color. Additionally, the combobox can be interacted with and colors can be 
//     set by clicking on the combobox contents. If the color is not valid, then a alert is displayed. 
// Good design is shown by binding being used in order to update the combobox, significantly reducing the amount
//      of code needed to use this feature. 
// Additionally, methods are well named and small for easy readability and follow SOLID
// Resource files are also utiilized as well as a custom exception handling class
// Classes which could create a bound textfield and combobox were considered but decided to be not worthwhile
//     due to the amount of parameters and dependencies needed
// Reflection was also considered for user input, but built in functionality of Color.web() proved more useful (line 69)
// 60 lines
package gui.executables.background;
import error.SlogoAlert;
import gui.executables.FireableButton;
import gui.executables.TextAreaWriter;
import gui.executables.boxes.Palette;
import gui.language.Language;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
/** Subclass of BackgroundColorChooser which implements it as Textbox for color entry
 *  as well as a combobox which displays entered colors and can also change background color by interaction
 * @author Bilva
 */
public class BackgroundWriteBox extends BackgroundComboBox {
	private Color currentColor;
	private TextField inputText;
	private SimpleObjectProperty<ObservableList<String>> usedColors;
	/** Creates textfield and combobox with binding to a list they both use. Both objects change the currentColor which is what is sent for display
	 * @param runButton Fireable runbutton
	 * @param t Encapsulating object of command textArea
	 * @param l Encapsulation of current language in workspace
	 * @param p Object storing data structure with current color palette representation
	 */
	public BackgroundWriteBox(TextAreaWriter t,Language l,FireableButton runButton, Palette p){
		super(t,l,runButton,p);
		initializeButtons();
		displayedNodes.add(1,inputText);
	}
	private void initializeButtons(){
		createBindedComboBox();
		createTextField();
	}
	private void createBindedComboBox(){
		usedColors = new SimpleObjectProperty<>(FXCollections.observableArrayList());
		colorPicker.itemsProperty().bind(usedColors);
		colorPicker.valueProperty().addListener((x, y, newValue) -> currentColor=Color.web(newValue));
	}
	private void createTextField(){
		inputText = new TextField();
		inputText.setPromptText(myButtonResources.getString("BackgroundTextField"));
		inputText.setOnAction(e -> {
			try{
				handleTextInput();
			}
			catch(IllegalArgumentException y){
				SlogoAlert alert=new SlogoAlert(myAlertResources.getString("InvalidColorEntry"),y.getMessage());
				alert.showAlert();
			}
		});
	}
	private void handleTextInput(){
		currentColor = Color.web(inputText.getText());
		executeCommand();
		if (!usedColors.getValue().contains(inputText.getText())){
			usedColors.getValue().add(inputText.getText());
		}
		inputText.clear();
	}
	/**
	 * The color used in getCommands() is currentColor which is updated in various ways by other nodes
	 */
	protected Color generateColor() {
		return currentColor;
	}
}