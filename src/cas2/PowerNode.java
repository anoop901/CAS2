/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cas2;

/**
 * Represents an expression to the power of another expression.
 * @author anoop
 */
public class PowerNode extends AlgebraNode {
	private AlgebraNode base;
	private AlgebraNode exponent;
	
	public PowerNode(AlgebraNode base, AlgebraNode exponent) {
		this.base = base;
		this.exponent = exponent;
	}

	@Override
	public AlgebraNode differentiate(VariableNode variable) {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
	}

	@Override
	public String toInfix() {
		
		String left = base.toInfix();
		String right = exponent.toInfix();
		
		if (base instanceof AdditionNode
				|| base instanceof SubtractionNode
				|| base instanceof MultiplicationNode
				|| base instanceof DivisionNode
				|| base instanceof PowerNode) {
			left = "(" + left + ")";
		}
		
		if (exponent instanceof AdditionNode
				|| exponent instanceof SubtractionNode
				|| exponent instanceof MultiplicationNode
				|| exponent instanceof DivisionNode) {
			right = "(" + right + ")";
		}
		
		return left + "^" + right;
	}
}
