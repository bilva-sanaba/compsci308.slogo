package GUI;

import java.util.ResourceBundle;

public enum Languages {
	
	CHINESE ("Chinese"),
	ENGLISH ("English"),
	FRENCH ("French"),
	GERMAN ("German"),
	ITALIAN ("Italian"),
	PORTUGESE ("Portugese"),
	RUSSIAN ("Russian"),
	SPANISH ("Spanish");
	
	private String language;
	private ResourceBundle Translations;
	public static final String DEFAULT_RESOURCES_PACKAGE = "resources.languages/";
	Languages(String lang){
		language = lang;
		
	}
	
	public ResourceBundle getResourceBundle(){
		return ResourceBundle.getBundle(DEFAULT_RESOURCES_PACKAGE + language);
	}
}
