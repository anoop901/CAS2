/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cas2;

import java.util.logging.Level;

/**
 * Represents the sum of 2 expressions.
 * @author anoop
 */
public class AdditionNode extends AlgebraNode {
	
	private AlgebraNode leftAddend;
	private AlgebraNode rightAddend;
	
	public AdditionNode(AlgebraNode leftAddend, AlgebraNode rightAddend) {
		this.leftAddend = leftAddend;
		this.rightAddend = rightAddend;
	}

	@Override
	public AlgebraNode differentiate(VariableNode variable) {
		return new AdditionNode(leftAddend.differentiate(variable), rightAddend.differentiate(variable));
	}

	@Override
	public String toInfix() {
		return leftAddend.toInfix() + " + " + rightAddend.toInfix();
	}

	@Override
	public AlgebraNode simplify() {
		// simplify each addend
		AlgebraNode simpleLeft = leftAddend.simplify();
		AlgebraNode simpleRight = rightAddend.simplify();
		
		// if either addend is 0, ignore return the other one
		if (simpleLeft instanceof ConstantNode && ((ConstantNode) simpleLeft).getValue() == 0) {
			return simpleRight;
		}
		if (simpleRight instanceof ConstantNode && ((ConstantNode) simpleRight).getValue() == 0) {
			return simpleLeft;
		}
		
		return new AdditionNode(simpleLeft, simpleRight);
	}
}
