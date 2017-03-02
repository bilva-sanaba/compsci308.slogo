package GUI_RetrievableCode;

import java.util.Map;

import javafx.scene.Group;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;

public class VariableScrollPane {
	private Group root;
private ScrollPane myScrollPane;
private int startingHeight;

public VariableScrollPane(Map<String,Integer>variables){
	root=new Group();
	myScrollPane=new ScrollPane();
	myScrollPane.setContent(root);
	startingHeight=0;
for(String s:variables.keySet()){
		StackPane entry=new StackPane();
		Rectangle r=new Rectangle();
		Text t=new Text(s);
		r.setFill(Color.WHITE);
		 r.setStroke(Color.BLACK);
		 r.setWidth(myScrollPane.getPrefWidth());
		 r.setHeight(t.getBoundsInLocal().getHeight());
		 entry.getChildren().add(r);
		 entry.getChildren().add(t);
		 entry.setLayoutY(startingHeight);
		 entry.setOnMouseClicked( e-> changeVariable(entry));
	}

}
}
