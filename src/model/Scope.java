package model;

import configuration.Trajectory;
import model.commands.CommandException;
import model.commands.CommandFactory;

/**
 * This class deals with the scope of knowledge a 
 * Command has access to.
 * 
 * The scope includes:
 * - Access to trajectory
 * - Access to commands
 * - Access to variables
 * 
 * Commands make a scope request using the boolean constructro, 
 * and they will be sent an appropriate Scope.
 * 
 * @author DhruvKPatel
 */
public class Scope {
	
	private CommandFactory commands;
	private VariableContainer variables;
	private Trajectory trajectory;

	/**
	 * This constructor is to send actual scope to commands
	 * @param commands
	 * @param variables
	 * @param trajectory
	 * @param request
	 */
	public Scope(CommandFactory commands, VariableContainer variables, Trajectory trajectory, Scope request){
		if(request.getCommands() != null) this.commands = commands;
		if(request.getVariables() != null) this.variables = variables;
		if(request.getTrajectory() != null) this.trajectory = trajectory;	
	}
	
	/**
	 * Copy request constructor
	 */
	public Scope(Scope old, Scope request){
		if(request.getCommands() != null) this.commands = old.getCommands();
		if(request.getVariables() != null) this.variables = old.getVariables();
		if(request.getTrajectory() != null) this.trajectory = old.getTrajectory();
	}
	
	/**
	 * This is the request constructor, which commands will use to designate
	 * what data they require.
	 * @param needsCommands
	 * @param needsVariables
	 * @param needsTrajectory
	 */
	public Scope(boolean needsCommands, boolean needsVariables, boolean needsTrajectory){
		try{
			if(needsCommands) this.commands = new CommandFactory();
		} 
		catch (CommandException e){
			// Will never occur - only using to get default value
		}
		if(needsVariables) this.variables = new VariableContainer();
		if(needsTrajectory) this.trajectory = new Trajectory();
	}
	
	public CommandFactory getCommands(){
		return commands;
	}
	
	public VariableContainer getVariables(){
		return variables;
	}
	
	public Trajectory getTrajectory(){
		return trajectory;
	}

}
