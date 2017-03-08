package GUI;

import java.util.Arrays;
import java.util.List;

public class LanguageFactory {
	private List<String> Languages = Arrays.asList("English","Chinese","French","German","Italian","Portugese","Russian","Spanish");
	public List<String> getLanguages(){
		return Languages;
	}
}
