package model.commands.displayCommands;

import model.configuration.TurtleState;

public class SetPenColor extends TurtleDisplayCommand {

	@Override
	public void makeQuickChange(int index, TurtleState turtle) {
		turtle.setPenColor(index);
	}

	@Override
	public String getID() {
		return "SetPenColor";
	}

}
