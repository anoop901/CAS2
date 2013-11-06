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
		return new MultiplicationNode(
				new AdditionNode(
					new DivisionNode(
						new MultiplicationNode(
							base.differentiate(variable),
							exponent),
						base),
					new MultiplicationNode(
						exponent.differentiate(variable),
						new FunctionNode(
							Function.LN,
							base))),
				this);
	}
	
	@Override
	public AlgebraNode simplify() {
		AlgebraNode simpleLeft = base.simplify();
		AlgebraNode simpleRight = exponent.simplify();
		
		// if base is 0 and exponent is not 0, return 0
		if ((simpleLeft instanceof ConstantNode && ((ConstantNode) simpleLeft).getValue() == 0)
				&& !(simpleRight instanceof ConstantNode && ((ConstantNode) simpleRight).getValue() == 0)) {
			return new ConstantNode(0);
		}
		
		// if base is 1, return 1
		if (simpleLeft instanceof ConstantNode && ((ConstantNode) simpleLeft).getValue() == 1) {
			return new ConstantNode(1);
		}
		
		// if base is not 0 and exponent is 0, return 1
		if (!(simpleLeft instanceof ConstantNode && ((ConstantNode) simpleLeft).getValue() == 0)
				&& (simpleRight instanceof ConstantNode && ((ConstantNode) simpleRight).getValue() == 0)) {
			return new ConstantNode(1);
		}
		
		// if exponent is 1, return base
		if (simpleRight instanceof ConstantNode && ((ConstantNode) simpleRight).getValue() == 1) {
			return simpleLeft;
		}
		
		return new PowerNode(simpleLeft, simpleRight);
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
