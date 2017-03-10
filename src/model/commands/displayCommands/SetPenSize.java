package model.commands.displayCommands;

import model.configuration.TurtleState;

public class SetPenSize extends TurtleDisplayCommand {

	@Override
	public void makeQuickChange(int index, TurtleState turtle) {
		turtle.setPenSize(index);
	}

	@Override
	public String getID() {
		return "SetPenSize";
	}

}
