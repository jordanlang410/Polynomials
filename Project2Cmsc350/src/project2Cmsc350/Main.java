/* Name: Lang, Jordan
 * Project Name: Project2Cmis350
 * Date: 04/4/2021
 * Description: This class creates the GUI for the user to select 
 * a polynomial file from. It then implements the orderedList and Polynomial
 * classes to output the polynomials and whether they are strong or weak sorted
*/
package project2Cmsc350;

import javax.swing.*;

import java.util.*;
import java.io.File;
import java.io.FileNotFoundException;

public class Main {

	private static ArrayList<Polynomial> polynomialList = new ArrayList<>();

	public static ArrayList<Polynomial> polyFromText() {

		// Use JFileChooser to allow user to select file 
		JFileChooser fc = new JFileChooser();

		fc.setCurrentDirectory(new java.io.File(".")); // use current directory
		fc.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
		if (fc.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
			File file = new File(fc.getSelectedFile().getAbsolutePath());

			try {
				// uses polynomial string from file and places into arraylist
				Scanner sc = new Scanner(file);
				while (sc.hasNextLine()) {
					String info = sc.nextLine();
					Polynomial poly = new Polynomial(info);
					polynomialList.add(poly);
				}
				sc.close();
				
				// catch errors and exit program if necessary
			} catch (InvalidPolynomialSyntax e) {
				JOptionPane.showMessageDialog(null, e.getMessage());
				System.exit(0);
			} catch (FileNotFoundException e) {
				JOptionPane.showMessageDialog(JOptionPane.getRootFrame(), "Could not find file.");
				System.exit(0);
			}
		}
		return polynomialList;

	}

	// check if polynomials are strong sorted
	public static void checkStrong() {
		boolean strongOrder = true;
		try {
			for (int i = 0; i < polynomialList.size() - 1; i++) {
				if (polynomialList.get(i).compareTo(polynomialList.get(i + 1)) < 0) {
					strongOrder = false;
				}

			}
		} catch (InvalidPolynomialSyntax e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
		}
		System.out.println("Strong Order: " + strongOrder);
	}

	// Unfortunately, cannot figure out why isSorted always returns true
	// Supposed to check if polynomials are weak sorted using orderedList
	public static void checkWeak() {
		boolean isSorted = false;
		try {
			for (int i = 0; i < polynomialList.size(); i++) {
				isSorted = OrderedList.checkSorted(polynomialList.get(i).iterator(), new Comparator<Polynomial>() {

					@Override
					public int compare(Polynomial o1, Polynomial o2) {

						return o1.head.getExpo() - o2.head.getExpo();
					}
				});

			}
		} catch (InvalidPolynomialSyntax e) {
			JOptionPane.showMessageDialog(null, e.getMessage());

		}
		System.out.println("Weak Order: " + isSorted);

	}

	public static void displayPolynomial() {

		try {
			//display polynomials
			ArrayList<Polynomial> a = polyFromText();
			for (int i = 0; i < polynomialList.size(); i++) {
				System.out.println(polynomialList.get(i).toString());
			}

		} catch (InvalidPolynomialSyntax e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
		}

	}

	public static void main(String[] args) {
		displayPolynomial();
		checkStrong();
		checkWeak();

	}

}
