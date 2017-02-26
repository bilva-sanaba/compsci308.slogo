package model.commands;

import java.lang.reflect.Constructor;
import java.util.HashMap;

/**
 * Creates commands from a Command ID.
 * Will automatically register initialize all
 * commands from resource file.
 * 
 * @author DhruvKPatel
 */
public class CommandFactory {
	private static CommandFactory instance = new CommandFactory();
	HashMap<String, Class<? extends Command>> registeredCommands  = new HashMap<String, Class<? extends Command>>();
	
	/**
	 * Constructs an empty command factory.
	 * Next, fills the factory automatically with 
	 * Default Commands (the ones given in the resource files)
	 */
	public CommandFactory(){
		
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
	public Command constructCommand(String commandID) throws CommandException{
		Class<? extends Command> clazz = registeredCommands.get(commandID);
//		Constructor<? extends Command> commandConstructor;
		
		if(clazz == null) throw new CommandException(String.format("Command not found: %s", commandID));

		try {
//			commandConstructor = clazz.getDeclaredConstructor(registeredCommands.get(commandID));
			return registeredCommands.get(commandID).newInstance();
		} catch (Exception e) {
			e.printStackTrace();
			throw new CommandException(String.format("Command class is broken: %s", commandID));
		}
	}
	
	/**
	 * Returns singleton instance for factory
	 * (this singleton may not be the best method for this, but it's what the example said)
	 * @return
	 */
	public static CommandFactory getInstance(){
		return instance;
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
