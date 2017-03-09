package xml;

import java.util.HashMap;
import java.util.Map;

import javafx.scene.paint.Paint;

public class XMLData {
		public static final String IMAGE_ELEMENT = "image";
	    public static final String BACKGROUNDCOLOR_ELEMENT="backgroundColor";
	    public static final String PENCOLOR_ELEMENT="penColor";
	    public static final String LANGUAGE_ELEMENT="language";
	private String myImageString;
	private String myBackgroundColorString;
	private String myPenColorString;
	private String myLanguageString;
public XMLData(String imageString,Paint backgroundColor,Paint penColor,String language){
	myImageString=imageString;
	myBackgroundColorString=backgroundColor.toString();
	myPenColorString=penColor.toString();
	myLanguageString=language;
}
public Map<String,String>getParameters(){
	Map<String,String> map=new HashMap<String,String>();
	map.put(IMAGE_ELEMENT,myImageString);
	map.put(BACKGROUNDCOLOR_ELEMENT,myBackgroundColorString);
	map.put( PENCOLOR_ELEMENT, myPenColorString);
	map.put(LANGUAGE_ELEMENT,myLanguageString);
	return map;
}

}
