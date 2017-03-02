package model.commands.advancedCommands;

import java.awt.List;

import configuration.Trajectory;
import configuration.TurtleState;
import model.Arguments;
import model.Constant;
import model.Interpreter;
import model.Scope;
import model.TList;
import model.Token;
import model.TokenType;
import model.commands.AbstractCommand;
import model.commands.CommandException;
import parser.TokenNode;
/**
 * 
 * @author Jacob Weiss
 *
 */
public class Repeat extends AbstractCommand {

	@Override
	public Scope getScopeRequest() {
		return new Scope(true, true, true);
	}

	@Override
	public double execute(Arguments args) throws CommandException {
		Trajectory trajectory = getScope().getTrajectory();
		TurtleState current = trajectory.getLast().getModifiableCopy();
		
		double times = args.getDouble(0);
		TList tList= args.getTList(1);
		double result=0;
		
		while(times>1){
			TList t = tList;
			TokenNode root = t.getChildren().get(0);
			Interpreter i = new Interpreter();
			Arguments innerArgs = new Arguments();
			Token ans = i.evaluateTree(root, getScope());
			innerArgs.add(ans);
			//result = execute(innerArgs);
			times-=1;
		}
		
		return result;
	}

	@Override
	public Arguments getDefaultArgs() {
		Token[] def = {new Constant(0), new TList()};
		return new Arguments(def);
	}

	@Override
	public String getID() {
		// TODO Auto-generated method stub
		return "Repeat";
	}

}
