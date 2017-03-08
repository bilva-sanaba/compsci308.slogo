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
import parser.tokenNodes.TokenNode;
/**
 * 
 * @author Jacob Weiss
 *
 */
public class IfElse extends AbstractCommand {

	@Override
	public Scope getScopeRequest() {
		return new Scope(true, true, true, true);
	}

	@Override
	public double execute(Arguments args, Scope scope) throws CommandException {
		int check = (int) args.getDouble(0);
		TList trueList= args.getTList(1);
		TList falseList = args.getTList(2);
		double result = 0;
		
		if(check!=0){
			Constant r = (Constant)trueList.evaluateContents(scope).getLast();
			result = r.getVal();
		}
		else{
			Constant r = (Constant)falseList.evaluateContents(scope).getLast();
			result = r.getVal();
		}
		
		return result;
	}

	@Override
	public Arguments getDefaultArgs() {
		Token[] def = {new Constant(0), new TList(), new TList()};
		return new Arguments(def);
	}

	@Override
	public String getID() {
		// TODO Auto-generated method stub
		return "If";
	}

}

