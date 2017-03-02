package model.commands.mathOperations;

import java.util.Random;

import model.Arguments;
import model.Constant;
import model.Token;

/**
 * Returns random non-negative number strictly less than argument
 * 
 * Note: This class is named "TRandom" to avoid naming conflicts with "Random" library
 * @author DhruvKPatel
 */
public class TRandom extends OneParamOperation {

	@Override
	public double execute(Arguments args) {
		Random r = new Random();
		return r.nextDouble() * args.getDouble(0);
	}

	@Override
	public String getID() {
		return "Random";
	}

}
