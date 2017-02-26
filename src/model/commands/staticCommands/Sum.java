package model.commands.staticCommands;

import configuration.Arguments;
import model.commands.Command;
import model.commands.CommandFactory;

public class Sum extends StaticCommand {

	static{
		CommandFactory.getInstance().registerCommand("SUM", Sum.class.asSubclass(Command.class));
	}
	
	@Override
	public int doLogic(Arguments args) {
		
	}

	@Override
	public Arguments getDefaultArgs() {
		Arguments args = new Arguments();
		args.add(0);
		args.add(0);
		return args;
	}

	@Override
	public String getID() {
		return "SUM";
	}

}
