package model.commands.displayCommands;

import model.configuration.TurtleState;

public class SetShape extends TurtleDisplayCommand {

	@Override
	public void makeQuickChange(int index, TurtleState turtle) {
		turtle.setShape(index);
	}

	@Override
	public String getID() {
		return "SetShape";
	}

}
