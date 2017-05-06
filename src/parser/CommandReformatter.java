package parser;

import parser.regularExpressions.ProgramParser;
/**
 * Reformats command by removing new lines, etc.
 * @author Jacob Weiss
 *
 */
public class CommandReformatter {
	private final String NEWLINE = "Newline";
	private final String WHITESPACE = "Whitespace";
	private final String COMMENT = "Comment";
	
	private final String SYNTAX = "Syntax";
	private final String DEFAULT_RESOURCES_PACKAGE = "resources.languages/";
	
	private final String SPACE = " ";
	
	private ProgramParser parser = new ProgramParser();
	
	public CommandReformatter(){
		parser.addPatterns(DEFAULT_RESOURCES_PACKAGE + SYNTAX);
	}
	
	public String reformatCommand(String command) {
		String ans = "";
		for(int i = 0; i < command.length(); i++){
			String str = command.substring(i, i+1);
			if (parser.getSymbol(str).equals(NEWLINE) || parser.getSymbol(str).equals(WHITESPACE)){
				ans = ans + SPACE;
			}
			else if(parser.getSymbol(str).equals(COMMENT)){
				ans = ans + SPACE;
				i = i + findNextLineIndex(command.substring(i));
			}
			else{
				ans = ans + str;
			}
		}
		return ans.toLowerCase();
	}
	

	private int findNextLineIndex(String sub) {
		for(int k=0; k < sub.length(); k++){
			String str = sub.substring(k, k+1);
			if(parser.getSymbol(str).equals(NEWLINE)){
				return k;
			}
		}
		return 0;
	}

	public ProgramParser getParser(){
		return parser;
	}
}
