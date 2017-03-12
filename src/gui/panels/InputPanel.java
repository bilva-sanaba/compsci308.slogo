package gui.panels;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;

import resources.*;
import xml.Default;
import gui.ButtonMaker;
import gui.GUI;
import gui.executables.FireableButton;
import gui.executables.TextAreaWriter;
import gui.executables.background.BackgroundColorChooser;
import gui.executables.background.BackgroundColorWriteBox;
import gui.executables.boxes.Palette;
import gui.executables.boxes.TurtleComboBox;
import gui.executables.pencolor.PenColorChooser;
import gui.executables.pencolor.PenColorPicker;
import gui.executables.pencolor.RandomPenColorButton;
import gui.executables.penproperties.PenSizeChooser;
import gui.executables.penproperties.PenSizeTextInput;
import gui.executables.penproperties.PenToggle;
import gui.language.Language;
import gui.language.LanguageFactory;
import gui.movement.TurtleViewManager;
import gui.panels.dynamic.CommandScrollPane;
import javafx.beans.value.ChangeListener;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Shape;
import javafx.scene.web.WebView;
import javafx.stage.Stage;
/**
 * Creates InputPanel to be displayed on GUI
 * @author Bilva
 * @author Alex
 *
 */
public class InputPanel {
	private BackgroundColorChooser cb;
	private Pane returnPanel;
	private GridPane inputPanel = new GridPane();
	private Language currentLanguage = new Language("English");
	private ComboBox<String> languages;
	private PenColorChooser pb;
	private static final String HELP_WINDOW_TITLE="Syntax";
	private static final String HELP_URL="/resources/help.html";
	private static final int BUTTON_SPACING = 20;
	private TurtleViewManager tvm;
	private TurtleComboBox tcb;
	private ButtonMaker buttonMaker= new ButtonMaker();
	private Palette myPalette;
	private HBox topButtons;
	private TextAreaWriter textAreaWriter;
	private Button runButton;
	private FireableButton fireButton;
	/**
	 * Creates the input panel to be displayed in GUI
	 * @param Instance of original Turtle in GUI
	 * @param otherButtons buttons created in GUI which must be displayed in inputpanel
	 * @param myDefault Default preferences
	 * @param t Texta area box to be displayed
	 * @param rb Runbutton to be added to Input panel display
	 * @param p Palette of Colors for colobuttons
	 */
	public InputPanel(TurtleViewManager TVM, List<Button> otherButtons, Default myDefault,TextAreaWriter t,Button rb, Palette p){
		runButton=rb;
		fireButton = new FireableButton(runButton);
		myPalette = p;
		tvm=TVM;
		textAreaWriter=t;
		returnPanel = initInputPanel(otherButtons);
		returnPanel.setPrefSize(GUI.GUI_WIDTH,GUI.GUI_HEIGHT/3);
		addBackgroundButton();
		addOtherBoxes(myDefault);
		addPenButton();
		addExtraButtons();
		setLanguage(myDefault.getLanguage());

	}
	/**
	 * Needed by GUI to appropriately update TVM
	 * @return
	 */
	public TurtleComboBox getTurtleComboBox(){
		return tcb;
	}
	/**
	 * Used by GUI to add panel
	 * @return Pane 
	 */
	public Pane getBottomPanel(){
		return returnPanel;
	}
	private Pane initInputPanel(List<Button> otherButtons) {
		BorderPane bottomPanel = new BorderPane();
		Button help=buttonMaker.createButton("Help",e -> handleHelpButton());
		bottomPanel.setCenter(inputPanel);
		Pane controlButtons = new HBox(BUTTON_SPACING);
		controlButtons.getChildren().addAll(otherButtons);
		controlButtons.getChildren().add(help);
		inputPanel.setConstraints(controlButtons,0,0);
		inputPanel.getChildren().add(controlButtons);
		bottomPanel.getStyleClass().add("pane");
		//bottomPanel.setMinHeight(GUI_Configuration.SCENE_WIDTH*3/16);
		return bottomPanel;
	}
	private void addBackgroundButton(){
		cb = new BackgroundColorWriteBox(textAreaWriter,currentLanguage,fireButton,myPalette);
		HBox topButtons = new HBox(BUTTON_SPACING);
		topButtons.getChildren().addAll(cb.getChooser());
		inputPanel.setConstraints(topButtons,0,3);
		inputPanel.getChildren().add(topButtons);
	}
	private void addOtherBoxes(Default myDefault){
		//try to use lambdas for this
		tcb = new TurtleComboBox(textAreaWriter,currentLanguage,fireButton, myDefault);
		ComboBox<String>turtleChoice=tcb.getTurtleChooser();
		Pane theBoxes = new HBox(BUTTON_SPACING);
		inputPanel.setConstraints(theBoxes,0,1);
		inputPanel.getChildren().add(theBoxes);
		theBoxes.getChildren().add(turtleChoice);
		theBoxes.getChildren().add(createLanguageBox());
		theBoxes.getChildren().add(myPalette.getPalette());
	}
	private void addPenButton(){
		pb = new PenColorPicker(textAreaWriter,currentLanguage,fireButton,myPalette);
		placePenButton();

	}
	private  void placePenButton(){
		topButtons = new HBox(BUTTON_SPACING);
		topButtons.getChildren().addAll(pb.getChooser());
		inputPanel.setConstraints(topButtons,0,2);
		inputPanel.getChildren().add(topButtons);
	}


	private PenSizeChooser createPenSizeButton(){
		PenSizeChooser p = new PenSizeTextInput(textAreaWriter,fireButton,currentLanguage);
		return p;
	}
	private PenToggle createPenToggle(){
		PenToggle p = new PenToggle(textAreaWriter,fireButton,currentLanguage);
		return p;
	}

	private void addExtraButtons(){
		List<Node> extraButtons = new ArrayList<Node>();
		Node penButton = createPenSizeButton().getPenButtons();
		Node penToggle = createPenToggle().getToggleButton();
		HBox penButtons = new HBox(penButton,penToggle);
		extraButtons.addAll(tvm.getExtraButtons());
		inputPanel.setConstraints(penButtons, 0,4);
		System.out.println(tvm.getButtonCount());
		for (int i=0; i < tvm.getButtonCount();i++){
			inputPanel.setConstraints(extraButtons.get(i),0,6-i);
		}
		inputPanel.getChildren().add(penButtons);
		inputPanel.getChildren().addAll(extraButtons);
	}

	private ComboBox<String> createLanguageBox() {
		List<String> Languages = (new LanguageFactory()).getLanguages();
		ComboBox<String> languageBox = createComboBox(Languages, (observable, oldValue, newValue) -> {
			setLanguage(Languages.get((newValue.intValue())));
		});
		languages=languageBox;
		return languageBox;
	}
	/**
	 * Used so save preferences can function
	 * @param d
	 * @throws ClassNotFoundException
	 * @throws InstantiationException
	 * @throws IllegalAccessException
	 * @throws NoSuchMethodException
	 * @throws SecurityException
	 * @throws IllegalArgumentException
	 * @throws InvocationTargetException
	 */
	public void updateDefaults(Default d) throws ClassNotFoundException, InstantiationException, IllegalAccessException, NoSuchMethodException, SecurityException, IllegalArgumentException, InvocationTargetException{
		setLanguage(d.getLanguage());
		updateImage(d);
		updateBackgroundColor(d);
	}
	public String getCurrentTurtleImage(){
		return tcb.getTurtleChooser().getSelectionModel().getSelectedItem();
	}
	private void updateBackgroundColor(Default d){

		Color color=Color.valueOf(d.getBackgroundColor());
		ResourceBundle myResources=ResourceBundle.getBundle(controller.Controller.DEFAULT_RESOURCE_BUNDLE+currentLanguage.getLanguage());
		String command=myResources.getString("SetPalette").split("\\|")[0];
		command+=(" "+Integer.toString(myPalette.getPalette().getItems().size()+1)+ " ");
		command+=(Double.toString(color.getRed()*255)+" ");
		command+=(Double.toString(color.getGreen()*255)+" ");
		command+=(Double.toString(color.getBlue()*255)+" ");
		command+=(myResources.getString("SetBackground").split("\\|")[0]);
		command+=(" "+Integer.toString(myPalette.getPalette().getItems().size()+1));
		textAreaWriter.setText(command);
		fireButton.fire();
	}

	private void updateImage(Default d){
		tcb.getTurtleChooser().getSelectionModel().select(d.getImageString().get(0));
	}
	private void setLanguage(String language){
		currentLanguage.setLanguage(language);
		languages.getSelectionModel().select(language);
	}
	/**
	 * used so GUI can get current Language so  that controller can call it to send to backend
	 * @return
	 */
	public String getCurrentLanguage(){
		return currentLanguage.getLanguage();
	}
	private ComboBox<String> createComboBox(List<String> items, ChangeListener<? super Number> listener) {
		ComboBox<String> cb = createComboBox(items);
		cb.getSelectionModel().selectedIndexProperty().addListener(listener);
		return cb;
	}
	private ComboBox<String> createComboBox(List<String> items) {
		ComboBox<String> cb = new ComboBox<String>(FXCollections.observableArrayList(items));
		cb.getSelectionModel().selectFirst();
		return cb;
	}
	private void handleHelpButton(){
		Group root = new Group();
		WebView browser = new WebView();
		Scene helpScene = new Scene(root);
		Stage helpStage = new Stage();
		helpStage.setTitle(HELP_WINDOW_TITLE);
		helpStage.setScene(helpScene);
		URL url = getClass().getResource(HELP_URL);
		browser.getEngine().load(url.toExternalForm());
		root.getChildren().add(browser);
		helpStage.show();
	}
}