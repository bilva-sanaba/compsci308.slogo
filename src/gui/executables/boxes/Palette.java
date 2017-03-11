package gui.executables.boxes;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

import javafx.scene.control.ComboBox;
import javafx.scene.paint.Color;
/**
 * Function-less ComboBox displaying current accessible color palette
 * @author Alex
 *
 */
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
	/**
	 * 
	 * @return ComboBox containg available color palette
	 */
	public ComboBox<String> getPalette(){
		return myPaletteBox;
	}
	/**
	 * Configures ComboBox to display new palette
	 * 
	 * @param newPalette Map containing Integer keys ArrayList rgb representation of
	 * Color 
	 */
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
	/**
	 * 
	 * @param index for evaluation
	 * @return Color corresponding to index
	 */
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
