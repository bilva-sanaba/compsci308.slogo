package GUI_RetrievableCode;

import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;

public class CommandScrollPane {
	private Group root;
private ScrollPane myScrollPane;
private int startingHeight;
private TextArea textArea;
private Button run;
public CommandScrollPane(TextArea t, Button play){
	root=new Group();
	 textArea=t; 
	myScrollPane=new ScrollPane();
	myScrollPane.setContent(root);
	startingHeight=0;
	run=play;
}
public ScrollPane getScrollPane(){
	return myScrollPane;
}
public void clearScrollPane(){
	root.getChildren().clear();
}
public void addText(){
	String myString=textArea.getText();
	Text t=new Text(myString);
	 t.setWrappingWidth(myScrollPane.getWidth()*.9);
	 RetrievableStackPane entry=new RetrievableStackPane(myString);
	 Rectangle r=new Rectangle();
	 r.setFill(Color.WHITE);
	 r.setStroke(Color.BLACK);
	 r.setWidth(myScrollPane.getPrefWidth());
	 r.setHeight(t.getBoundsInLocal().getHeight());
	 entry.getStackPane().getChildren().add(r);
	 entry.getStackPane().getChildren().add(t);
	 entry.getStackPane().setLayoutY(startingHeight);
	 entry.getStackPane().setOnMouseClicked( e-> reInputText(entry));
	 startingHeight+=entry.getStackPane().getBoundsInLocal().getHeight();
	 root.getChildren().add(entry.getStackPane());
}
private void reInputText(RetrievableStackPane sp){
	textArea.clear();
	textArea.setText(sp.getString());
	run.fire();
}
}
