package gui.language;

import java.util.Arrays;
import java.util.List;
/**
 * 
 * @author Alex encapsulation of languages
 *
 */
public class LanguageFactory {

	private List<String> Languages = Arrays.asList("English","Chinese","French","German","Italian","Portuguese","Russian","Spanish");
	/**
	 * 
	 * @return list of allowed languages
	 */
	public List<String> getLanguages(){
		return Languages;
	}
}
