/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cas2;

/**
 * 
 * @author anoop
 */
public class FunctionNode extends AlgebraNode {
	
	private Function function;
	private AlgebraNode argument;
	
	public FunctionNode(Function function, AlgebraNode argument) {
		this.function = function;
		this.argument = argument;
	}

	@Override
	public AlgebraNode differentiate(VariableNode variable) {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
	}

	@Override
	public String toInfix() {
		return function.getName() + "(" + argument.toInfix() + ")";
	}
	
	
}
