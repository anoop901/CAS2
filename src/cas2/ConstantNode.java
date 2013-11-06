/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cas2;

/**
 * Represents a constant, like 3.
 * @author anoop
 */
public class ConstantNode extends AlgebraNode {
	
	private double value;
	
	public ConstantNode(double value) {
		this.value = value;
	}

	@Override
	public AlgebraNode differentiate(VariableNode variable) {
		return new ConstantNode(0);
	}

	@Override
	public String toInfix() {
		int floor = (int) value;
		if (floor == value) {
			return Integer.toString(floor);
		} else {
			return Double.toString(value);
		}
	}
	
	public double getValue() {
		return value;
	}

	@Override
	public AlgebraNode simplify() {
		return this;
	}
}
