package model.commands.turtleCommands;

import configuration.Trajectory;
import configuration.TurtleState;
import model.Arguments;
import model.Scope;
import model.commands.AbstractCommand;
import model.commands.CommandException;

public class ClearScreen extends AbstractCommand{

	public double execute(Arguments args) throws CommandException {
		Trajectory trajectory = getScope().getTrajectory();
		TurtleState current = trajectory.getLast().getModifiableCopy();
		double xLoc = current.getX();
		double yLoc = current.getY();
		
		double newX = 0;
		double newY = 0;
				
		double distanceTraveled = Math.sqrt(Math.pow(Math.abs(newX - xLoc), 2) + Math.pow(Math.abs(newY - yLoc), 2));

		current.setX(newX);
		current.setY(newY);
		trajectory.clear();
		trajectory.addLast(current);
		return distanceTraveled;
	}

	
	public Arguments getDefaultArgs() {
		return new Arguments();
	}


	public String getID() {
		// TODO Auto-generated method stub
		return "ClearScreen";
	}
	
	public Scope getScopeRequest(){
		return new Scope(true, true, true);
	}

}
