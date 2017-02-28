package model;

import model.commands.CommandException;
import model.commands.CommandFactory;
import parser.TokenNode;

public class treeTester {

	public static void main(String[] args) throws CommandException {
		CommandFactory f = new CommandFactory();
		
		TokenNode s1 = new TokenNode(f.getCommand("Sum"));
		s1.addChild(new Constant(1));
		s1.addChild(new Constant(2));
		
		TokenNode s2 = new TokenNode(f.getCommand("Sum"));
		s2.addChild(new Constant(3));
		s2.addChild(new Constant(4));
		
		TokenNode s = new TokenNode(f.getCommand("Sum"));
		s.addChild(s1);
		s.addChild(s2);

		Interpreter i = new Interpreter();
		Token result = i.evaluateTree(s);
		
		System.out.println(result);
	}

}
