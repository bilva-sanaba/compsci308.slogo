package model.commands.mathOperations;

import model.commands.AbstractCommand;
import model.configuration.Scope;

/**
 * Class for all Math Operation Commands.
 * This has no purpose but to encapsulate all Math commands.
 * If a method similar to all math commands is needed, 
 * it will be added here.
 * 
 * Note: this also includes all Boolean Operations, because they follow the same format.
 * @author DhruvKPatel
 *
 */
public abstract class MathOperation extends AbstractCommand {
	public Scope getScopeRequest(){
		return new Scope(false, false, false, false);
	}
}
