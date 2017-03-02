package GUI;

import java.util.Map;

import javafx.scene.Group;
import javafx.scene.control.Alert;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputDialog;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import model.commands.CommandException;

public class VariableScrollPane {
	private Group root;
private ScrollPane myScrollPane;
private int startingHeight;
private Map<String,Integer> myVariables;

public VariableScrollPane() {
	root=new Group();
	myScrollPane=new ScrollPane();
	myScrollPane.setContent(root);
	startingHeight=0;
}
public void add(Map<String,Integer>variables){
	myVariables=variables;
for(String s:myVariables.keySet()){
		
	StackPane entry=new StackPane();
	FlowPane textPane=new FlowPane();	
	
	Rectangle r=new Rectangle();
		Text text=new Text("    "+s+":  ");
		TextField t=new TextField(Integer.toString(variables.get(s)));
		t.setMaxWidth(30);
		t.setOnKeyPressed(e->changeVariable(e.getCode(),s,t));
		r.setFill(Color.WHITE);
		 r.setStroke(Color.BLACK);
		 r.setWidth(myScrollPane.getPrefWidth());
		 r.setHeight(text.getBoundsInLocal().getHeight()+10);
		 textPane.setPrefWidth(t.getMaxWidth()+text.getBoundsInLocal().getWidth());
		 textPane.getChildren().add(text);
		 textPane.getChildren().add(t);
		 entry.setMaxWidth(myScrollPane.getPrefWidth()-1);
		 entry.getChildren().add(r);
		 entry.getChildren().add(textPane);
		 entry.setLayoutY(startingHeight);
		 startingHeight+=entry.getBoundsInLocal().getHeight()+12;
		 root.getChildren().add(entry);
	}

}
public ScrollPane getScrollPane(){
	return myScrollPane;
}
public void clearScrollPane(){
	root.getChildren().clear();
}
private void changeVariable(KeyCode code,String s,TextField t){
	if(code==KeyCode.ENTER){
		try{
			Integer i=Integer.parseInt(t.getText());
			myVariables.put(s,i);
			System.out.println(myVariables);
		}
		catch(NumberFormatException e){
			Alert alert= new Alert(AlertType.ERROR);
			alert.setTitle("Please enter a valid number");
			alert.setContentText(e.getMessage()); 
			alert.showAndWait();
			t.setText(Integer.toString(myVariables.get(s)));
			
		}
	}
}
}
