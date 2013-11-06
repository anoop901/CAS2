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
