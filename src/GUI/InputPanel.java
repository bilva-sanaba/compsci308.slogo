package GUI;

import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import ColorChoosers.BackgroundColorChooser;
import ColorChoosers.PenColorChooser;
import GUI_BackgroundColorChooser.BackgroundColorWriteBox;
import GUI_PenColorButton.PenColorPicker;
import GUI_RetrievableCode.CommandScrollPane;
import GUI_TurtleMovers.TurtleViewManager;
import javafx.beans.value.ChangeListener;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Shape;
import javafx.scene.web.WebView;
import javafx.stage.Stage;

public class InputPanel {
	private Node returnPanel;
	private GridPane inputPanel = new GridPane();
	private String currentLanguage = "English";
	private PenColorChooser pb;
	private static final String HELP_WINDOW_TITLE="Syntax";
	private static final String HELP_URL="help.html";
	private static final int BUTTON_SPACING = 20;
	public static final List<String> Languages = Arrays.asList("English","Chinese","French","German","Italian","Portugese","Russian","Spanish");
public InputPanel(TurtleViewManager tvm, List<Button> otherButtons,Shape background){
	returnPanel = initInputPanel(otherButtons);
	addBackgroundButton(background);
	addOtherBoxes(tvm);
	addPenButton(tvm);
	addExtraButtons(tvm);
	}
public Node getBottomPanel(){
	return returnPanel;
}
private Node initInputPanel(List<Button> otherButtons) {
 	
 	BorderPane bottomPanel = new BorderPane();
    Button help=createButton("Help",e -> handleHelpButton());
    bottomPanel.setCenter(inputPanel);
    Pane controlButtons = new HBox(BUTTON_SPACING);
    controlButtons.getChildren().addAll(otherButtons);
    controlButtons.getChildren().add(help);
    inputPanel.setConstraints(controlButtons,0,0);
    inputPanel.getChildren().add(controlButtons);
    return bottomPanel;
}
private void addBackgroundButton(Shape background){
	BackgroundColorChooser cb = new BackgroundColorWriteBox(background);
	HBox topButtons = new HBox(BUTTON_SPACING);
	topButtons.getChildren().addAll(cb.getChooser());
	inputPanel.setConstraints(topButtons,0,3);
    inputPanel.getChildren().add(topButtons);
}
private void addOtherBoxes(TurtleViewManager tvm){
 TurtleComboBox tcb = new TurtleComboBox(tvm);
 ComboBox<ImageView>turtleChoice=tcb.getTurtleChooser();
 Pane theBoxes = new HBox(BUTTON_SPACING);
 inputPanel.setConstraints(theBoxes,0,1);
 inputPanel.getChildren().add(theBoxes);
   theBoxes.getChildren().add(turtleChoice);
   theBoxes.getChildren().add(createLanguageBox());
}
private void addPenButton(TurtleViewManager tvm){
	pb = new PenColorPicker(tvm);
	HBox topButtons = new HBox(BUTTON_SPACING);
	topButtons.getChildren().addAll(pb.getChooser());
	 inputPanel.setConstraints(topButtons,0,2);
	 inputPanel.getChildren().add(topButtons);
}
public static Label createLabel(String text) {
    Label label = new Label(text);
    label.setTextFill(Color.BLACK);
    return label;
    
}
private void addExtraButtons(TurtleViewManager tvm){
	List<Node> extraButtons = new ArrayList<Node>();
	extraButtons.addAll(tvm.getExtraButtons());
	if (extraButtons.size()!=0){
		inputPanel.setConstraints(extraButtons.get(0),0,5);
		inputPanel.setConstraints(extraButtons.get(1),0,4);
	}
	 inputPanel.getChildren().addAll(extraButtons);
}

private ChoiceBox<String> createLanguageBox() {
	ChoiceBox<String> language = createChoiceBox(Languages, (observable, oldValue, newValue) -> {
        setLanguage(newValue.toString());
        });
	return language;
}
private void setLanguage(String language){
	currentLanguage=language;
}
public String getCurrentLanguage(){
	return currentLanguage;
}
private ChoiceBox<String> createChoiceBox(List<String> items, ChangeListener<? super Number> listener) {
    ChoiceBox<String> cb = createChoiceBox(items);
    cb.getSelectionModel().selectedIndexProperty().addListener(listener);
    return cb;
}
private ChoiceBox<String> createChoiceBox(List<String> items) {
    ChoiceBox<String> cb = new ChoiceBox<String>(FXCollections.observableArrayList(items));
    cb.getSelectionModel().selectFirst();
    return cb;
}
private Button createButton(String label, EventHandler<ActionEvent> e) {
    Button b = new Button();
    b.setText(label);
    b.setOnAction(e);
    return b;
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