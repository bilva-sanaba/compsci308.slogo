import configuration.Arguments;
import model.commands.Command;
import model.commands.CommandException;
import model.commands.CommandFactory;
import model.commands.staticCommands.Sum;

public class tester {

	public static void main(String[]args) {
		CommandFactory f = CommandFactory.getInstance();
		
		try {
			Class.forName(Sum.class.getName());
			
//			System.out.println(f);
			
			
			Command c = f.constructCommand("SUM");

			Arguments a = new Arguments();
			a.add(4);
			a.add(6);
			int x = c.execute(a);
			System.out.println(x);
			
			
			Integer l = 0;
			Integer r = 4;
			System.out.println(l.getClass());
			System.out.println(r.getClass());
			System.out.println(l.getClass());
			Object w = 9;
			w.getClass().getTypeName();
			
			
			
			
			
			
		} catch (ClassNotFoundException | CommandException e) {
			e.printStackTrace();
		}
		
	}

}
