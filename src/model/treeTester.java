package model;

import configuration.Trajectory;
import configuration.TurtleState;
import model.commands.CommandException;
import model.commands.CommandFactory;
import parser.TokenNode;

public class treeTester {

	public static void main(String[] args) throws CommandException {
		CommandFactory f = new CommandFactory();
		Trajectory traj = new Trajectory();
		
		TokenNode s1 = new TokenNode(f.getCommand("Sum"));
		s1.addChild(new Constant(1));
		s1.addChild(new Constant(2));
		
		TokenNode s2 = new TokenNode(f.getCommand("Sum"));
		s2.addChild(new Constant(3));
		s2.addChild(new Constant(4));
		
		TokenNode s = new TokenNode(f.getCommand("Sum"));
		s.addChild(s1);
		s.addChild(s2);

		TokenNode n = new TokenNode(f.getCommand("Not"));
		n.addChild(s);
		
		TokenNode q = new TokenNode(f.getCommand("Not"));
		q.addChild(n);
		
		
		
		Command twd = f.getCommand("SetTowards");
		traj.addLast(new TurtleState(100, 100, 0, false, false));
		twd.setScope(new Scope(f, new VariableContainer(), traj, twd.getScopeRequest()));
		TokenNode t = new TokenNode(twd);
		t.addChild(q);
		t.addChild(new Constant(1));
	
		Interpreter i = new Interpreter();
		System.out.println(i.evaluateTree(t));
		
		System.out.println(traj);
	}

}
