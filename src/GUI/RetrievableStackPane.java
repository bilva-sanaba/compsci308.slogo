package GUI;

import javafx.scene.layout.StackPane;

public class RetrievableStackPane {
private StackPane myStackPane;
private String myString;
public RetrievableStackPane(String s){
	myString=s;
	myStackPane=new StackPane();
}
public StackPane getStackPane(){
	return myStackPane;
}
public String getString(){
	return myString;
}
}
