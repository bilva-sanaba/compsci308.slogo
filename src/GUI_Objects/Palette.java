package GUI_Objects;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

import javafx.scene.control.ComboBox;
import javafx.scene.paint.Color;

public class Palette {
	private ComboBox<String> myPaletteBox=new ComboBox<String>();
	private Map<Integer, String> myPalette = new HashMap<Integer, String>();
	public static final String DEFAULT_RESOURCE_BUNDLE="resources/";
	public static final String COLORS="defaultPalette";
	private ResourceBundle myResources;
	public Palette(){
		myResources=ResourceBundle.getBundle(DEFAULT_RESOURCE_BUNDLE+COLORS);
		myPaletteBox.setCellFactory(c->new ColorListCell());
		for(String s:myResources.keySet()){
			myPalette.put(Integer.parseInt(s), s + ":"+ myResources.getString(s));
			myPaletteBox.getItems().add(s + ":" + myResources.getString(s));
		}
		myPaletteBox.setPromptText("Color Palette");

	}
	public ComboBox<String> getPalette(){
		return myPaletteBox;
	}
	public void setPalette(Map<Integer, ArrayList<Integer>> newPalette){
		for (Integer x : newPalette.keySet()){
			if (myPalette.containsKey(x)){
				myPalette.remove(x);
				myPalette.put(x, x+":"+ColorConverter(newPalette.get(x)));
			}else{
				myPalette.put(x, x+":"+ColorConverter(newPalette.get(x)));
			}
		}

		myPaletteBox.getItems().clear();
		myPaletteBox.getItems().addAll(myPalette.values());
	}
	public Color evalPalette(int index){
		
		if (myPalette.containsKey(index)){
			return Color.valueOf(myPalette.get(index).split(":")[1]);
		}
		return null;
	}
	private String ColorConverter(ArrayList<Integer> list){
		String returnString = "rgb(";
		for (Integer i : list){
			returnString = returnString + i.toString() + ",";
		}
		returnString = returnString.substring(0, returnString.length()-1)+")";
		return returnString;
	}
}
