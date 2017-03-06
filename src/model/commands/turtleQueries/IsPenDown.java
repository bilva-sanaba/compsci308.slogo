package model.commands.turtleQueries;

import configuration.Trajectory;
import configuration.TurtleState;
import model.Arguments;
import model.Constant;
import model.Token;
import model.commands.CommandException;
import model.commands.turtleCommands.NoParamCommand;
import model.commands.turtleCommands.TurtleCommand;
/**
 * 
 * @author Jacob Weiss
 *
 */
public class IsPenDown extends NoParamCommand {

	@Override
	public double execute(Arguments args) throws CommandException {
		Trajectory trajectory = getScope().getTrajectory();
		TurtleState current = trajectory.getLast().getModifiableCopy();
		if(current.penDown()){
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
