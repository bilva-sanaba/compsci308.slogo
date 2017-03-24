package gui.executables.boxes;
//this code is part of my masterpiece
//The palette represents the defined colors the user can access with back-end commands
//The palette is represented by a view-only ComboBox, clicking it does nothing
//I wanted the ComboBox to display the color to the user instead of merely a String, ideally through a rectangle of the color
//However, ComboBoxes are not properly equipped to support Nodes, and are liable to cause them to disappear upon
//selection or duplication 
//Thus, a listcell was created as a factory for the combobox
//This factory uses Color.valueOf() on a string representation of a color (either colorweb or rgb)
//and generates a new rectangle whose fill attribute is of the corresponding color
// the combobox sets this factory through a lambda expression generating a new ColorListCell
//The initial palette is contained entirely in a resource file, allowing for easy modification of the default palette
//the setPalette public method allows the back-end to dynamically alter the color corresponding to any index
//This includes defining new colors for previously undefined indices.
//The evalPalette public method returns the color corresponding to a given index, and returns null if the color is 
//undefined (allowing for error handling elsewhere
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
