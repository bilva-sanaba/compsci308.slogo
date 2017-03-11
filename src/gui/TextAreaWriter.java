package gui;

import javafx.scene.control.TextArea;

public class TextAreaWriter {
private TextArea myTextArea;
public TextAreaWriter(TextArea textArea){
myTextArea=textArea;
}
public String getText(){
	return myTextArea.getText();
}
public void setText(String s){
	myTextArea.setText(s);
}
public void setMaxWidth(double d){
	myTextArea.setMaxWidth(d);
}
public void setMinWidth(double d){
	myTextArea.setMinWidth(d);
}
public void clear(){
	myTextArea.clear();
}
}
