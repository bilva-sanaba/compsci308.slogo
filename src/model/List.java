package model;

import model.commands.CommandException;

public class List implements Token {
	
	private Arguments contents;
	
	public List(){
		
	}
	
	@Override
	public TokenType getType() {
		return TokenType.LIST;
	}

	@Override
	public Token evaluate(Arguments args) throws CommandException {
		contents = args;
		return this;
	}
	
	public Arguments getContents() throws CommandException{
		if(contents == null) throw new CommandException("Trying to access contents of an empty List");
		return contents;
	}
	
	public String toString(){
		String s = "[ ";
		for(Token t: contents){
			s += t.toString() + " ";
		}
		s += " ]";
		return s;
	}

}
