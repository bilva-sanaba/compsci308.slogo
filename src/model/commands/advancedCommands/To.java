package model.commands.advancedCommands;

import model.Arguments;
import model.TList;
import model.commands.AbstractCommand;
import model.commands.CommandException;

/**
 * Class for "TO" command
 * @author DhruvKPatel
 *
 */
public class To extends AbstractCommand {

	@Override
	public double execute(Arguments args) throws CommandException {
		TemplateCommand newCommand = (TemplateCommand)args.get(0);
		TList parameters = (TList)args.get(1);
		TList contents = (TList)args.get(2);
		return 1;
	}

	/**
 	 * Expected Arguments:
	 * 1: Command (Template)
	 * 2: List 
	 * 3: List
	 */
	@Override
	public Arguments getDefaultArgs() {
		Arguments args = new Arguments();
		args.add(new TemplateCommand());
		args.add(new TList());
		args.add(new TList());
		return args;
	}

	@Override
	public String getID() {
		return "To";
	}

}
