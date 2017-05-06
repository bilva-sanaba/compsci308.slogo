package model.configuration;

import model.World;
import model.commands.CommandFactory;
import model.exceptions.CommandException;
import model.tokens.VariableContainer;

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
	private World world;

	/**
	 * This constructor is to send actual scope to commands
	 * @param commands
	 * @param variables
	 * @param trajectory
	 * @param request
	 * @throws CommandException 
	 */
	public Scope(CommandFactory commands, VariableContainer variables, Trajectory trajectory, World world, Scope request) throws CommandException{
		if(request.commands != null) this.commands = commands;
		if(request.variables != null) this.variables = variables;
		if(request.trajectory != null) this.trajectory = trajectory;	
		if(request.world != null) this.world = world;
	}
	
	/**
	 * Copy request constructor
	 * @throws CommandException 
	 */
	public Scope(Scope old, Scope request) throws CommandException{
		this(old.commands, old.variables, old.trajectory, old.world, request);
	}
	
	/**
	 * This is the request constructor, which commands will use to designate
	 * what data they require.
	 * @param needsCommands
	 * @param needsVariables
	 * @param needsTrajectory
	 */
	public Scope(boolean needsCommands, boolean needsVariables, boolean needsTrajectory, boolean needsWorld){
		try{
			if(needsCommands) this.commands = new CommandFactory();
		} 
		catch (CommandException e){
			// Will never occur - only using to get default value
		}
		if(needsVariables) this.variables = new VariableContainer();
		if(needsTrajectory) this.trajectory = new Trajectory();
		if(needsWorld) this.world = new World(trajectory, variables, commands);
	}
	
	public CommandFactory getCommands() throws CommandException{
		checkForAccess(commands, "command factory");
		return commands;
	}
	
	public VariableContainer getVariables() throws CommandException{
		checkForAccess(variables, "variable container");
		return variables;
	}
	
	public Trajectory getTrajectory() throws CommandException{
		checkForAccess(trajectory, "trajectory");
		return trajectory;
	}
	
	public World getWorld() throws CommandException{
		checkForAccess(world, "world");
		return world;
	}
	
	private void checkForAccess(Object accessor, String name) throws CommandException{
//		if(accessor == null) throw new CommandException(String.format("Access to %s denied", name));
	}

}
