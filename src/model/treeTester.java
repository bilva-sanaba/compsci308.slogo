package model;

import configuration.Trajectory;
import configuration.TurtleState;
import model.commands.CommandException;
import model.commands.CommandFactory;
import parser.SlogoParser;
import parser.TokenNode;

public class treeTester {

	public static void main(String[] args) throws CommandException {
		
		String command = "sum 1 2 sum 3 4 sum sum 1 2 sum 3 4";
		
		SlogoParser parser = new SlogoParser();
		TokenNode root = parser.parse(new TokenNode(null, new TList()), command);

		CommandFactory f = new CommandFactory();
		VariableContainer v = new VariableContainer();
		Trajectory t = new Trajectory();
		t.addLast(new TurtleState(1, 1, 0, false, false));
				
		Interpreter i = new Interpreter();
		
		Token ans = new TList();
		
		for(TokenNode cmd: root.getChildren()){
			ans = i.evaluateTree(cmd, null);
			System.out.println(ans);
		}
		
		System.out.println(t);
		
	}
}
