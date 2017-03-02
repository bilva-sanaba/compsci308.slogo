package model;
import configuration.Trajectory;
import configuration.TurtleState;
import model.commands.CommandException;
import model.commands.CommandFactory;
import parser.SlogoParser;
import parser.TokenNode;
public class treeTester {
	public static void main(String[] args) throws CommandException {
		
		Model m = new SlogoModel();
		System.out.println(m.getTrajectory("towards 10 10"));
	}
}