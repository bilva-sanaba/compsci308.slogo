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
public class Repeat extends AbstractCommand {

	@Override
	public Scope getScopeRequest() {
		return new Scope(true, true, true);
	}

	@Override
	public double execute(Arguments args) throws CommandException {
		Trajectory trajectory = getScope().getTrajectory();
		VariableContainer vars = new VariableContainer();
		
		int times = (int) args.getDouble(0);
		TList tList= args.getTList(1);
		double result = 0;
		int repcount=1;
		
		for(int i=times; i>0; i--){
			Constant r = (Constant) tList.executeChildren().getLast();
			result = r.getVal();
			vars.set(new Variable("repcount"), new Constant(repcount));
			repcount++;
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
