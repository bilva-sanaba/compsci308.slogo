import configuration.Arguments;
import configuration.Trajectory;
import configuration.TurtleState;
import model.Command;
import model.Constant;
import model.Token;
import model.commands.CommandException;
import model.commands.CommandFactory;
import model.commands.DynamicTurtleCommand;

/**
 * Tester class for model
 * @author DhruvKPatel
 *
 */
public class tester {

	public static void main(String[]args) {
		try {
			
			// Create factory for all commands
			CommandFactory factory = new CommandFactory();	
			
			// Get instance of a command
			Command command = factory.getCommand("SetTowards");
			
			// Set current trajectory (one default turtleState element)
			Trajectory trajectory = new Trajectory();
			trajectory.addLast(new TurtleState());
			
			// Print current trajectory
			System.out.println("1\n" + trajectory.toString());
			
			
			// Execute command with arguments and trajectory
			Token[] arguments = {
					new Constant(-0.1),
					new Constant(0)
			};
			
			if(command.needsTurtleTrajectory()){
				DynamicTurtleCommand commandD = (DynamicTurtleCommand) command;
				((DynamicTurtleCommand) commandD).setTrajectory(trajectory);
				commandD.execute(new Arguments(arguments));
			}
			
			// Print new trajectory			
			System.out.println("2\n" + trajectory.toString());
	
		} catch (CommandException e) {
			e.printStackTrace();
		}
		
	}
}