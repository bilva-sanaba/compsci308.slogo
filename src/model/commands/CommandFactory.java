package model.commands;

import java.util.HashMap;
import java.util.ResourceBundle;

import model.Command;

/**
 * Creates commands from a Command ID.
 * Will automatically register initialize all
 * commands from resource file.
 * 
 * This class is based on the "factory" design pattern:
 * http://www.oodesign.com/factory-pattern.html
 * 
 * @author DhruvKPatel
 */
public class CommandFactory {
	private final ResourceBundle DEFAULT_COMMANDS = ResourceBundle.getBundle("resources/Commands");
	private HashMap<String, Class<? extends Command>> registeredCommands  = new HashMap<String, Class<? extends Command>>();
	
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
			} catch (ClassNotFoundException e) {
				throw new CommandException(String.format("Command not found: %s", commandID));
			}
			registerCommand(commandID, key.asSubclass(Command.class));
		}
	}
	
	/**
	 * Constructs a command factory copy
	 * @param original
	 * @throws CommandException 
	 */
	public CommandFactory(CommandFactory original) throws CommandException{
		this();
		registeredCommands = new HashMap<String, Class<? extends Command>>(original.registeredCommands);
	}
	
	/**
	 * Adds command class to registry, paired with
	 * command's String ID.
	 */
	public void registerCommand(String commandID, Class<? extends Command> clazz){
		registeredCommands.put(commandID, clazz);
	}
	
	
	/**
	 * Checks registry for command ID.
	 * If ID exists, constructs a command object for that ID.
	 * If ID doesn't exist, returns null
	 * Reflection is utilized to simplify instantiation
	 * @throws CommandException 
	 */
	public Command getCommand(String commandID) throws CommandException{
		Class<? extends Command> clazz = registeredCommands.get(commandID);
		
		if(clazz == null) throw new CommandException(String.format("Command not found: %s", commandID));

		try {
			return registeredCommands.get(commandID).newInstance();
		} catch (Exception e) {
			throw new CommandException(String.format("Command could not initialize: %s", commandID));
		}
	}

	/**
	 * Prints current commands listed for debugging
	 */
	public String toString(){
		String s = "Command Factory:\n";
		for(String cmd : registeredCommands.keySet()) s += String.format("\t %s: \"%s\"\n", cmd, registeredCommands.get(cmd).getName());
		return s;
	}
}
