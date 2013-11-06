/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cas2;

/**
 * Represents a variable, like x.
 * @author anoop
 */
public class VariableNode extends AlgebraNode {
	
	private String name;
	
	public VariableNode(String name) {
		this.name = name;
	}

	@Override
	public AlgebraNode differentiate(VariableNode variable) {
		return this.equals(variable) ? new ConstantNode(1) : new ConstantNode(0);
	}

	@Override
	public String toInfix() {
		return name;
	}
	
	public boolean equals(VariableNode v2) {
		return this.name.equals(v2.name);
	}

	@Override
	public AlgebraNode simplify() {
		return this;
	}
}
