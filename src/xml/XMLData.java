package xml;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringJoiner;

import javafx.scene.paint.Paint;

public class XMLData {
		public static final String IMAGE_ELEMENT = "imageList";
	    public static final String BACKGROUNDCOLOR_ELEMENT="backgroundColor";
	    public static final String PENCOLOR_ELEMENT="penColor";
	    public static final String LANGUAGE_ELEMENT="language";
	private String myImageString;
	private String myBackgroundColorString;

	private String myLanguageString;
public XMLData(List<String> imageList,Paint backgroundColor,String language){
	myImageString=listToString(imageList);
	myBackgroundColorString=backgroundColor.toString();
	myLanguageString=language;
}
public Map<String,String>getParameters(){
	Map<String,String> map=new HashMap<String,String>();
	map.put(IMAGE_ELEMENT,myImageString);
	map.put(BACKGROUNDCOLOR_ELEMENT,myBackgroundColorString);
	map.put(LANGUAGE_ELEMENT,myLanguageString);
	return map;
}
private String listToString(List<String >list){
	 StringJoiner joiner = new StringJoiner(" ", "", "");
	    list.forEach(joiner::add);

	   return joiner.toString();
}
}
