package model.commands.dynamicTurtle.advancedCommands;

import model.Arguments;
import model.commands.AbstractCommand;
import model.commands.CommandException;

public class TemplateCommand extends AbstractCommand {
	
	private Arguments defaultArgs;
	private String id;
	private boolean needsTurtleTrajectory;
	
	public TemplateCommand(){
		defaultArgs = new Arguments();
		id = "TemplateCommand";
		needsTurtleTrajectory = false;
	}
	
	@Override
	public double execute(Arguments args) throws CommandException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Arguments getDefaultArgs() {
		return defaultArgs;
	}
	
	public void setDefaultArgs(Arguments defaultArgs){
		this.defaultArgs = defaultArgs;
	}

	@Override
	public String getID() {
		return this.id;
	}
	
	public void setID(String id){
		this.id = id;
	}

	@Override
	public boolean needsTurtleTrajectory() {
		return needsTurtleTrajectory;
	}
	
	public void setNeedsTurtleTrajectory(boolean needsTurtleTrajectory){
		this.needsTurtleTrajectory = needsTurtleTrajectory;
	}

}
