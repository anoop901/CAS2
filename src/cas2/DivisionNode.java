/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cas2;

/**
 * Represents the quotient of 2 expressions.
 * @author anoop
 */
public class DivisionNode extends AlgebraNode {
	
	private AlgebraNode dividend;
	private AlgebraNode divisor;
	
	public DivisionNode(AlgebraNode dividend, AlgebraNode divisor) {
		this.dividend = dividend;
		this.divisor = divisor;
	}

	@Override
	public AlgebraNode differentiate(VariableNode variable) {
		// careful with references?
		return new DivisionNode(
				new SubtractionNode(
					new MultiplicationNode(divisor, dividend.differentiate(variable)),
					new MultiplicationNode(divisor.differentiate(variable), dividend)),
				new PowerNode(divisor, new ConstantNode(2)));
	}
	
	@Override
	public AlgebraNode simplify() {
		AlgebraNode simpleLeft = dividend.simplify();
		AlgebraNode simpleRight = divisor.simplify();
		
		// if numerator is 0 and denominator is not 0, return 0
		if ((simpleLeft instanceof ConstantNode && ((ConstantNode) simpleLeft).getValue() == 0)
				&& !(simpleRight instanceof ConstantNode && ((ConstantNode) simpleRight).getValue() == 0)) {
			return new ConstantNode(0);
		}
		
		// if denominator is 1, return numerator
		if (simpleRight instanceof ConstantNode && ((ConstantNode) simpleRight).getValue() == 1) {
			return simpleLeft;
		}
		
		return new DivisionNode(simpleLeft, simpleRight);
	}

	@Override
	public String toInfix() {
		
		String left = dividend.toInfix();
		String right = divisor.toInfix();
		
		if (dividend instanceof AdditionNode
				|| dividend instanceof SubtractionNode) {
			left = "(" + left + ")";
		}
		
		if (divisor instanceof AdditionNode
				|| divisor instanceof SubtractionNode
				|| divisor instanceof MultiplicationNode
				|| divisor instanceof DivisionNode) {
			right = "(" + right + ")";
		}
		
		return left + "/" + right;
	}
}
