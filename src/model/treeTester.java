package model;

import java.util.List;

import configuration.Trajectory;
import configuration.TurtleState;
import model.commands.CommandException;
import model.commands.CommandFactory;
import model.commands.DynamicTurtleCommand;
import parser.SlogoParser;
import parser.TokenNode;

public class treeTester {

	public static void main(String[] args) throws CommandException {
		CommandFactory f = new CommandFactory();
		Trajectory traj = new Trajectory();
		
		TokenNode s1 = new TokenNode(null,f.getCommand("Sum"));
		s1.addChild(new Constant(1));
		s1.addChild(new Constant(2));
		
		TokenNode s2 = new TokenNode(null,f.getCommand("Sum"));
		s2.addChild(new Constant(3));
		s2.addChild(new Constant(4));
		
		TokenNode s = new TokenNode(null,f.getCommand("Sum"));
		s.addChild(s1);
		s.addChild(s2);

		TokenNode n = new TokenNode(null,f.getCommand("Not"));
		n.addChild(s);
		
		TokenNode q = new TokenNode(null,f.getCommand("Not"));
		q.addChild(n);
		
		
		
		DynamicTurtleCommand twd = (DynamicTurtleCommand) f.getCommand("SetTowards");
		traj.addLast(new TurtleState(100, 100, 0, false, false));
		twd.setTrajectory(traj);
		TokenNode t = new TokenNode(null, twd);
		t.addChild(q);
		t.addChild(new Constant(1));
	
		Interpreter i = new Interpreter();
		
		SlogoParser parser = new SlogoParser();
		
		TokenNode qt = parser.parse(new TokenNode(null, new TList()), "towards not not sum sum 1 2 sum 3 4");
		
		System.out.println(i.evaluateTree(t));
		
		System.out.println(traj);
	}

}
