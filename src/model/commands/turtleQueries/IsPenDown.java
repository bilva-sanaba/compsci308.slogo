package model.commands.turtleQueries;

import model.Token;
import model.commands.turtleCommands.NoParamCommand;
import model.commands.turtleCommands.TurtleCommand;
import model.configuration.Arguments;
import model.configuration.Scope;
import model.configuration.Trajectory;
import model.configuration.TurtleState;
import model.exceptions.CommandException;
import model.tokens.Constant;
/**
 * 
 * @author Jacob Weiss
 *
 */
public class IsPenDown extends NoParamCommand {

	@Override
	public double execute(Arguments args, Scope scope) throws CommandException {
		Trajectory trajectory = scope.getTrajectory();
		TurtleState current = trajectory.getLast().getModifiableCopy();
		if(current.isPenDown()){
			return 1;
		}
		else{
			return 0;
		}
	}


	@Override
	public String getID() {
		// TODO Auto-generated method stub
		return "PenDown";
	}

}
