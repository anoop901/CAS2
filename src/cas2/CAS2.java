/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cas2;

import java.util.NoSuchElementException;
import java.util.Scanner;

/**
 *
 * @author anoop
 */
public class CAS2 {

	/**
	 * @param args the command line arguments
	 */
	public static void main(String[] args) {
		
		Scanner scnr = new Scanner(System.in);
		
		while (true) {
			System.out.print("Type an expression: ");
			try {
				AlgebraNode rootNode = AlgebraNode.parsePrefix(scnr.nextLine());
				System.out.println(rootNode);
				System.out.println(rootNode.differentiate(new VariableNode("x")));
			} catch (NoSuchElementException nsee) {
				System.out.println("Invalid prefix notation");
			}
			System.out.println();
		}
	}
}
