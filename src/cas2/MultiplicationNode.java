/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cas2;

/**
 * Represents the product of 2 expressions.
 * @author anoop
 */
public class MultiplicationNode extends AlgebraNode {
	
	public AlgebraNode leftFactor;
	public AlgebraNode rightFactor;
	
	public MultiplicationNode(AlgebraNode leftFactor, AlgebraNode rightFactor) {
		this.leftFactor = leftFactor;
		this.rightFactor = rightFactor;
	}

	@Override
	public AlgebraNode differentiate(VariableNode variable) {
		return new AdditionNode(
				new MultiplicationNode(leftFactor, rightFactor.differentiate(variable)),
				new MultiplicationNode(leftFactor.differentiate(variable), rightFactor));
	}

	@Override
	public String toInfix() {
		
		String left = leftFactor.toInfix();
		String right = rightFactor.toInfix();
		
		if (leftFactor instanceof AdditionNode
				|| leftFactor instanceof SubtractionNode) {
			left = "(" + left + ")";
		}
		
		if (rightFactor instanceof AdditionNode
				|| rightFactor instanceof SubtractionNode) {
			right = "(" + right + ")";
		}
		
		return left + "*" + right;
	}

	@Override
	public AlgebraNode simplify() {
		AlgebraNode simpleLeft = leftFactor.simplify();
		AlgebraNode simpleRight = rightFactor.simplify();
		
		if (simpleLeft instanceof ConstantNode && ((ConstantNode) simpleLeft).getValue() == 0
				|| simpleRight instanceof ConstantNode && ((ConstantNode) simpleRight).getValue() == 0) {
			return new ConstantNode(0);
		}
		
		if (simpleLeft instanceof ConstantNode && ((ConstantNode) simpleLeft).getValue() == 1) {
			return simpleRight;
		}
		if (simpleRight instanceof ConstantNode && ((ConstantNode) simpleRight).getValue() == 1) {
			return simpleLeft;
		}
		return new AdditionNode(simpleLeft, simpleRight);
	}
}
