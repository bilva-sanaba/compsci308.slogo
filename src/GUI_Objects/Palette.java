package GUI_Objects;

import java.util.ResourceBundle;

import javafx.scene.control.ComboBox;
import javafx.scene.paint.Color;

public class Palette {
	private ComboBox<String>	myPalette=new ComboBox<String>();
	public static final String DEFAULT_RESOURCE_BUNDLE="resources/";
	public static final String COLORS="defaultPalette";
	private ResourceBundle myResources;
public Palette(){
myResources=ResourceBundle.getBundle(DEFAULT_RESOURCE_BUNDLE+COLORS);
myPalette.setCellFactory(c->new ColorListCell());
for(String s:myResources.keySet()){
	myPalette.getItems().add(myResources.getString(s));
	
}
myPalette.setPromptText("Color Palette");
}
public ComboBox<String> getPalette(){
	return myPalette;
}
}
