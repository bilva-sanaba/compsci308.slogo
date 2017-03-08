//package model.commands.multipleTurtleCommands;
//
//import java.util.Set;
//
//import model.Arguments;
//import model.Command;
//import model.Scope;
//import model.TList;
//import model.commands.CommandException;
//
//public class AskWith extends AskCommand {
//
//	@Override
//	public double execute(Arguments args, Scope scope) throws CommandException {
//		Command ask = new Ask();
//		
//		TList condition = args.getTList(0);
//		
//		Set<Integer> allTurtles = scope.getWorld().getTurtles().keySet();
//	}
//
//
//	@Override
//	public String getID() {
//		return "AskWith";
//	}
//}
