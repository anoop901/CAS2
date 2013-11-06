/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cas2;

import java.util.Scanner;

/**
 * Abstract superclass for all objects that represent a part of an algebraic expression.
 * @author anoop
 */
public abstract class AlgebraNode {
	
	/**
	 * Factory method that returns the expression represented by a <code>String</code> in prefix notation.
	 * @param expr The prefix string to parse.
	 * @return
	 */
	public static AlgebraNode parsePrefix(String expr) {
		return parsePrefix(new Scanner(expr));
	}
	
	private static AlgebraNode parsePrefix(Scanner scnr) {
		
		String token = scnr.next();
		
		try {
			double value = Double.parseDouble(token);
			return new ConstantNode(value);
		} catch (NumberFormatException nfe) {
			if (token.equals("+")) {
				return new AdditionNode(parsePrefix(scnr), parsePrefix(scnr));
			} else if (token.equals("-")) {
				return new SubtractionNode(parsePrefix(scnr), parsePrefix(scnr));
			} else if (token.equals("*")) {
				return new MultiplicationNode(parsePrefix(scnr), parsePrefix(scnr));
			} else if (token.equals("/")) {
				return new DivisionNode(parsePrefix(scnr), parsePrefix(scnr));
			} else if (token.equals("^")) {
				return new PowerNode(parsePrefix(scnr), parsePrefix(scnr));
			} else {
				return new VariableNode(token);
			}
		}
	}
	
	/**
	 * Returns the (partial) derivative of this algebraic expression.
	 * @param variable The variable to differentiate with respect to.
	 * @return
	 */
	public abstract AlgebraNode differentiate(VariableNode variable);
	
	public AlgebraNode simplify() {
		return this;
	};
	
	/**
	 * Returns a string that represents this expression in infix notation.
	 * @return
	 */
	public abstract String toInfix();
	
	@Override
	public String toString() {
		return toInfix();
	}
}
