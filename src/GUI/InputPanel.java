package GUI;

import java.net.URL;
import java.util.Arrays;
import java.util.List;

import ColorChoosers.BackgroundColorChooser;
import ColorChoosers.PenColorChooser;
import GUI_BackgroundColorChooser.BackgroundColorWriteBox;
import GUI_PenColorButton.PenColorPicker;
import GUI_RetrievableCode.CommandScrollPane;
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
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Shape;
import javafx.scene.web.WebView;
import javafx.stage.Stage;

public class InputPanel {
	private Node returnPanel;
	private Pane inputPanel;
	private String currentLanguage = "English";
	private PenColorChooser pb;
	private static final String HELP_WINDOW_TITLE="Syntax";
	private static final String HELP_URL="help.html";
	public static final List<String> Languages = Arrays.asList("English","Chinese","French","German","Italian","Portugese","Russian","Spanish");
public InputPanel(TurtleViewManager tvm, List<Button> otherButtons,Shape background){
	returnPanel = initInputPanel(otherButtons);
	addBackgroundButton(background);
	addOtherBoxes(tvm);
	addPenButton(tvm);
}
public Node getBottomPanel(){
	return returnPanel;
}
private Node initInputPanel(List<Button> otherButtons) {
 	
 	BorderPane bottomPanel = new BorderPane();
    inputPanel = new FlowPane();
    bottomPanel.setCenter(inputPanel);
    inputPanel.getChildren().addAll(otherButtons);
    Button help=createButton("Help",e -> handleHelpButton());
    inputPanel.getChildren().add(help);
    return bottomPanel;
}
private void addBackgroundButton(Shape background){
	BackgroundColorChooser cb = new BackgroundColorWriteBox(background);
    inputPanel.getChildren().addAll(cb.getChooser());
}
private void addOtherBoxes(TurtleViewManager tvm){
 TurtleComboBox tcb = new TurtleComboBox(tvm);
 ComboBox<ImageView>turtleChoice=tcb.getTurtleChooser();
   inputPanel.getChildren().add(turtleChoice);
   inputPanel.getChildren().add(createLanguageBox());
}
private void addPenButton(TurtleViewManager tvm){
	pb = new PenColorPicker(tvm);
    inputPanel.getChildren().add(createLabel("Pick Pen Color: "));
	inputPanel.getChildren().addAll(pb.getChooser());
}
public static Label createLabel(String text) {
    Label label = new Label(text);
    label.setTextFill(Color.BLACK);
    return label;
    
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