/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cas2;

/**
 * An enum that represents all the possible unary functions.
 * @author anoop
 */
public enum Function {
	SIN		("sin"),
	COS		("cos"),
	TAN		("tan"),
	LN		("ln"),
	EXP		("exp"),
	SQRT	("sqrt");
	
	private String name;
	
	Function(String s) {
		name = s;
	}
	
	public String getName() {
		return name;
	}
	
	/**
	 * If there is a supported function with the name given, returns the function that has that name, if there is one.
	 * @param s the name
	 * @return the function with that name, or null if there isn't one.
	 */
	public static Function findFunctionWithName(String s) {
		for (Function f : Function.values()) {
			if (f.name.equals(s)) {
				return f;
			}
		}
		return null;
	}
}
