package gui.language;

public class Language {
private String myLanguage;
/**
 * Object to allow dynamic updating of languages
 * @param lang
 */
public Language(String lang){
	myLanguage=lang;
}
/**
 * 
 * @param lang new language to make object contain
 */
public void setLanguage(String lang){
	myLanguage=lang;
}
/**
 * 
 * @return language
 */
public String getLanguage(){
	return myLanguage;
}
}
