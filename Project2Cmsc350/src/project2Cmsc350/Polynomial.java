/* Name: Lang, Jordan
 * Project Name: Project2Cmis350
 * Date: 04/4/2021
 * Description: This class defines an individual polynomial and converts it to a readable string.
*/
package project2Cmsc350;

import java.util.ArrayList;
import java.util.Iterator;

public class Polynomial implements Iterable<Polynomial>, Comparable<Polynomial> {

	Node head;

	// Default Constructor
	public Polynomial() {
	}

	// Split the String up into individual nodes
	public Polynomial(String polynomial) throws InvalidPolynomialSyntax {
		head = null;
		String[] info = polynomial.split(" ");
		int i = 0;
		while (i < info.length) {
			try {
				if (head == null) { // first term
					// convert string to double/int as it is being read in
					head = new Node(Double.parseDouble(info[i]), Integer.parseInt(info[i + 1]));
				} else {
					Node sub = head;
					while (sub.next != null)
						sub = sub.next;
					// convert string to double/int as it is being read in
					sub.next = new Node(Double.parseDouble(info[i]), Integer.parseInt(info[i + 1]));
				}
				i += 2;
			} catch (Exception e) {
				throw new InvalidPolynomialSyntax("Invalid syntax.");
			}
		}
	}

	static class Node implements Iterable<Node>, Comparable<Node> {

		private int expo;
		private double coef;
		private Node next;

		Node(double coef, int expo) {
			next = null;
			this.expo = expo;
			this.coef = coef;
		}

		// Overload
		Node(int exp, double coeff, Node next) {
			this.next = next;
			this.expo = exp;
			this.coef = coeff;
		}

		// Getter Methods
		double getCoef() {
			return this.coef;
		}

		int getExpo() {
			return this.expo;
		}

		Node getNext() {
			return this.next;
		}

		@Override
		public Iterator<Node> iterator() {
			return new Iterator<Node>() { // iterator to traverse

				Node actual = new Node(expo, coef, next);

				// loop through until none left
				public boolean hasNext() {
					return actual != null;
				}

				public Node next() {
					if (hasNext()) {
						Node info = actual;
						actual = actual.next;
						return info;
					}
					return null;
				}
			};
		}

		@Override
		public int compareTo(Node o) {
			return this.expo - o.expo;
		}
	}

	// Compare the exponents and coefficients
	@Override
	public int compareTo(Polynomial obj) {
		Node mainCurrent = head;
		Node nextCurrent = obj.head;
		while (mainCurrent != null && nextCurrent != null) {
			if (mainCurrent.getExpo() < nextCurrent.getExpo()) {
				return 1; // positive indicates next arg is larger
			} else if (mainCurrent.getExpo() > nextCurrent.getExpo()) {
				return -1; // negative indicates next arg is smaller
			} else {
				if (mainCurrent.getCoef() < nextCurrent.getCoef()) {
					return 1;
				} else if (mainCurrent.getCoef() > nextCurrent.getCoef()) {
					return -1;
				}
			}

			// Reset mainCurrent and nextCurrent
			mainCurrent = mainCurrent.getNext();
			nextCurrent = nextCurrent.getNext();

		}
		if (mainCurrent == null && nextCurrent != null) {
			return -1;
		} else if (mainCurrent != null && nextCurrent == null) {
			return 1;
		}
		return 0;
	}

	// Convert polynomial provided via txt to String
	@Override
	public String toString() {
		String st = "";
		Iterator<Node> listIterator = head.iterator();
		while (listIterator.hasNext()) {
			Node curr = listIterator.next();
			if (curr.getExpo() > 1) {
				st += curr.getCoef() + "x^" + curr.getExpo();
			} else if (curr.getExpo() == 1) {
				st += curr.getCoef() + "x";
			} else {
				st += curr.getCoef();
			}
			curr = curr.getNext();
			if (curr != null) {
				st += " + ";
			}
		}
		return st;
	}

	@Override
	public Iterator<Polynomial> iterator() {
		ArrayList<Polynomial> nodes = new ArrayList<>();
		Iterator<Node> listIterator = head.iterator();
		while (listIterator.hasNext()) {
			Node curr = listIterator.next();
			Polynomial pol = new Polynomial();
			pol.head = curr;
			nodes.add(pol);
		}
		return nodes.iterator();
	}
}