package model.commands;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.ResourceBundle;

import model.exceptions.CommandException;
import model.tokens.Command;

/**
 * Creates commands from a Command ID.
 * Will automatically register initialize all
 * commands from resource file.
 * 
 * This class is based on the "factory" design pattern:
 * http://www.oodesign.com/factory-pattern.html
 * 
 * All commands are only initialized once. This is possible because commands do not rely on State.
 * They simply perform a function on input states.
 * 
 * @author DhruvKPatel
 */
public class CommandFactory {
	private final ResourceBundle DEFAULT_COMMANDS = ResourceBundle.getBundle("resources/Commands");
	private HashMap<String, Command> registeredCommands  = new HashMap<>();
	private List<String> runtimeAddedCommands = new ArrayList<>();
	
	private boolean defaultsAreFilled = false;
	
	/**
	 * Constructs an empty command factory.
	 * Next, fills the factory automatically with 
	 * Default Commands (the ones given in the resource files)
	 * @throws CommandException 
	 */
	public CommandFactory() throws CommandException{
		for(String commandID: DEFAULT_COMMANDS.keySet()){
			Class<?> key;
			try {
				key = Class.forName(DEFAULT_COMMANDS.getString(commandID).trim());
				registerCommand(commandID, key.asSubclass(Command.class).newInstance());
			} catch (ClassNotFoundException e) {
				throw new CommandException(String.format("Command not found: %s", commandID));
			} catch (InstantiationException | IllegalAccessException e) {
				throw new CommandException(String.format("Command is broken: %s", commandID));
			} 
		}
		defaultsAreFilled = true;
	}
	
	/**
	 * Constructs a command factory copy
	 * @param original
	 * @throws CommandException 
	 */
	public CommandFactory(CommandFactory original) throws CommandException{
		this();
		registeredCommands = new HashMap<>(original.registeredCommands);
	}
	
	/**
	 * Adds command to registry, paired with
	 * command's String ID.
	 * @throws CommandException 
	 * @throws IllegalAccessException 
	 * @throws InstantiationException 
	 */
	public void registerCommand(String commandID, Command cmd) throws CommandException {
		registeredCommands.put(commandID, cmd);
		if(defaultsAreFilled) runtimeAddedCommands.add(commandID);
	}
	
	/**
	 * Checks registry for command ID.
	 * If ID exists, gets command object for that ID.
	 * If ID doesn't exist, returns null
	 * @throws CommandException 
	 */
	public Command getCommand(String commandID) throws CommandException{
		Command cmd = registeredCommands.get(commandID);
		if(cmd == null) throw new CommandException(String.format("Command does not exist: %s", commandID));
		else return cmd;
	}
	
	/**
	 * Checks registry specifically for runtime-added commands
	 * returns whether the command exists in the registry.
	 */
	public boolean containsRuntimeCommand(String name){
		return runtimeAddedCommands.contains(name);
	}
	
	/**
	 * Prints current commands listed for debugging
	 */
	public String toString(){
		String s = "Commands:\n";
//		for(String cmd : registeredCommands.keySet()) s += String.format("\t %s: \"%s\"\n", cmd, registeredCommands.get(cmd).getID());
		for(String cmd : runtimeAddedCommands) s += String.format("\t %s: \"%s\"\n", cmd, cmd);

		return s;
	}

	/**
	 * Returns string IDs of user-added commands
	 */
	public Collection<String> getUserDefinedNames() {
		return runtimeAddedCommands;
	}
}
