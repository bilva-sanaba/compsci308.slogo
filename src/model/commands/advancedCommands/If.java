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
import model.Variable;
import model.VariableContainer;
import model.commands.AbstractCommand;
import model.commands.CommandException;
import parser.TokenNode;
/**
 * 
 * @author Jacob Weiss
 *
 */
public class If extends AbstractCommand {

	@Override
	public Scope getScopeRequest() {
		return new Scope(true, true, true);
	}

	@Override
	public double execute(Arguments args) throws CommandException {
		Trajectory trajectory = getScope().getTrajectory();
		
		int check = (int) args.getDouble(0);
		TList tList= args.getTList(1);
		double result = 0;
		
		if(check!=0){
			Constant r = (Constant)tList.executeChildren();
			result = r.getVal();
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
		return "If";
	}

}
