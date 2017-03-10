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
			myPalette.put(Integer.parseInt(s), myResources.getString(s));
			myPaletteBox.getItems().add(myResources.getString(s));
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
				myPalette.put(x, ColorConverter(newPalette.get(x)));
			}else{
				myPalette.put(x, ColorConverter(newPalette.get(x)));
			}
		}
		//	myPalette.clear();
		//	System.out.println(newPalette);
		//	int index = 0;
		//	for(String s:myResources.keySet()){
		//		index++;
		//		if (newPalette.containsKey(index)){
		//			myPalette.put(index,ColorConverter(newPalette.get(index)));
		//		}else{
		//			myPalette.put(index, myResources.getString(s));
		//		}
		//	}
		//	for (Integer x : newPalette.keySet()){
		//		if (!myPalette.containsKey(x)){
		//			System.out.println(ColorConverter(newPalette.get(x)));
		//			myPalette.put(x, ColorConverter(newPalette.get(x)));
		//		}
		//	}
		myPaletteBox.getItems().clear();
		myPaletteBox.getItems().addAll(myPalette.values());
	}
	public Color evalPalette(int index){
		
		if (myPalette.containsKey(index)){
			return Color.valueOf(myPalette.get(index));
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
