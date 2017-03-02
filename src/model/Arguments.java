package model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import model.commands.CommandException;

/**
 * This class is used to hold arguments (other commands) to pass to commands
 * 
 * Arguments will all be commands so it is easy to retrieve and store information
 * without having to cast on extraction.
 *
 * @author DhruvKPatel
 */
public class Arguments implements Iterable<Token>{
	
	private List<Token> arguments;
	
	/**
	 * Constructs a new argument list
	 * 
	 * The command is also stored, so that ID
	 * can be retrieved on invalid parameter error.
	 */
	public Arguments(Token[] args){
		arguments = new ArrayList<Token>(Arrays.asList(args));
	}
	
	public Arguments(){
		this(new Token[0]);
	}
	
	public Arguments(Token t){
		this();
		add(t);
	}
	/**
	 * Adds argument to end of list
	 */
	public void add(Token arg){
		arguments.add(arg);
	}
	
	/**
	 * Gets argument from list index
	 * @return 
	 */
	public Token get(int index){
		Token t = arguments.get(index); // This is two lines to prepare for extension
		return t;
	}
	
	
	public void set(int index, Token t){
		arguments.set(index, t);
	}
	
	/**
	 * Get number of arguments
	 */
	public int numArgs(){
		return arguments.size();
	}
	
	/**
	 * Compares argument types of two sets of arguments
	 * 
	 * To return true, set must have same length, and all
	 * corresponding arguments must have same type.
	 * @throws CommandException 
	 */
	public void checkForTypeDifferences(Arguments input) throws CommandException{
		if(input.numArgs() != numArgs()) throw new CommandException(String.format("Expected %d arguments, got %d", numArgs(), input.numArgs()));
		
		for(int i = 0; i < numArgs(); i++){
			TokenType myType = this.get(i).getType();
			TokenType inputType = input.get(i).getType();
			
			if((myType == TokenType.VARIABLE || myType == TokenType.CONSTANT) && (inputType == TokenType.VARIABLE || inputType == TokenType.CONSTANT));
			else if(!myType.equals(inputType)){
				throw new CommandException(String.format("Argument #%d: expected %s, got %s", i+1, myType, inputType));
			}
		}
	}
	
	/*
	 * The methods below are helpers to reduce repetitive casting
	 * in commands. I spent a lot of time trying to figure out how
	 * to dynamically return a generic type argument, but I could
	 * not figure it out.
	 * 
	 * Note: There is not "instanceof" error checking in this section.
	 * This is because runtime errors are already checked for. If 
	 * something goes wrong here, it is because of a faulty Command
	 * class, in which case the error should trace back here.
	 */
	
	/**
	 * Returns double from certain index.
	 * 
	 * If Token at index is not a Constant,
	 * this will return null.
	 * 
	 * Likewise, if an index doesn't exist,
	 * it will return null.
	 * @param index
	 * @return argument (double)
	 */
	public double getDouble(int index){
		return getConstant(index).getVal();
	}
	
	/**
	 * Returns variable from certain index.
	 * 
	 * If Token at index is not a Variable,
	 * this will return null.
	 * 
	 * Likewise, if an index doesn't exist,
	 * it will return null.
	 * @param index
	 * @return argument (variable)
	 */
	public Variable getVariable(int index){
		Token t = this.get(index);
		return ((Variable)t);
	}
	
	/**
	 * Returns Constant from certain index.
	 * 
	 * If Token at index is not a Constant,
	 * this will return null.
	 * 
	 * Likewise, if an index doesn't exist,
	 * it will return null.
	 * @param index
	 * @return argument (constant)
	 */
	public Constant getConstant(int index){
		Token t = this.get(index);
		
		if(t.getType() == TokenType.VARIABLE){
			return ((Variable)t).getValue();
		}
		return ((Constant)t);
		
	}
	
	/**
	 * Returns TList from certain index
	 */
	public TList getTList(int index){
		Token t = this.get(index);
		return ((TList)t);
	}
	
	/**
	 * Allows to be iterable
	 * @return
	 */
	@Override
	public Iterator<Token> iterator() {
		return arguments.iterator();
	}
	
	/**
	 * for debugging
	 */
	public String toString(){
		String s = "-----------\nArguments: ";
		for(Token t: this){
			s += t.toString() + "\n";
		}
		s += "-----------";
		return s;
	}
}
