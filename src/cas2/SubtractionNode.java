/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cas2;

/**
 * Represents the difference of 2 expressions.
 * @author anoop
 */
public class SubtractionNode extends AlgebraNode {
	
	private AlgebraNode minuend;
	private AlgebraNode subtrahend;
	
	public SubtractionNode(AlgebraNode minuend, AlgebraNode subtrahend) {
		this.minuend = minuend;
		this.subtrahend = subtrahend;
	}

	@Override
	public AlgebraNode differentiate(VariableNode variable) {
		return new SubtractionNode(minuend.differentiate(variable), subtrahend.differentiate(variable));
	}
	
	public AlgebraNode simplify() {
		// simplify minuend & subtrahend
		AlgebraNode simpleLeft = minuend.simplify();
		AlgebraNode simpleRight = subtrahend.simplify();
		
		// if subtrahend is 0, return minuend
		if (simpleRight instanceof ConstantNode && ((ConstantNode) simpleRight).getValue() == 0) {
			return simpleLeft;
		}
		
		return new SubtractionNode(simpleLeft, simpleRight);
	}

	@Override
	public String toInfix() {
		
		String left = minuend.toInfix();
		String right = subtrahend.toInfix();
		
		if (subtrahend instanceof AdditionNode
				|| subtrahend instanceof SubtractionNode) {
			right = "(" + right + ")";
		}
		
		return left + " - " + right;
	}
}
