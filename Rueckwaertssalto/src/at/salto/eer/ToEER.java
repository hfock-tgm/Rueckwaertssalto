package at.salto.eer;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import at.salto.metadaten.MetadatenHoover;
import at.salto.metadaten.MetadatenObject;
import at.salto.metadaten.hooverable;

/**
 * @author Hagen Fock 4AHIT
 * @author Michael Weinberger 4AHIT
 * @version 2015-01-28
 * @email hfock@student.tgm.ac.at
 * @email mweinberger@student.tgm.ac.at
 */
public class ToEER {
	private MetadatenHoover hoover;
	private PrintWriter out;
	private hooverable hb;
	private ArrayList<MetadatenObject> storagedObjects;

	/**
	 * 
	 */
	public ToEER(MetadatenHoover hoover) {
		this.hoover = hoover;
		this.storagedObjects = hoover.getObjects();
	}

	/**
	 * 
	 */
	public void startPrintWriter() {
		try {
			System.out.println("Starts the PrintWriter");

			out = new PrintWriter(new FileWriter("EER.dot"));

		} catch (IndexOutOfBoundsException e) {
			System.err.println("Caught IndexOutOfBoundsException: "
					+ e.getMessage());

		} catch (IOException e) {
			System.err.println("Caught IOException: " + e.getMessage());
		}
	}

	/**
	 * Schliesst den PrintWriter
	 */
	public void stopPrintWriter() {
		if (out != null) {
			System.out.println("Closing PrintWriter...");
			out.close();

		} else {
			System.out.println("PrintWriter not open");
		}
	}
	
	/**
	 * 
	 */
	public void doDotFile(){
		out.println("graph { "
		+ "rankdir=LR; "
		+ "a -- { b c d }; b -- { c e }; c -- { e f }; d -- { f g }; e -- h; "
		+ "f -- { h i j g }; g -- k; h -- { o l }; i -- { l m j }; j -- { m n k }; "
		+ "k -- { n r }; l -- { o m }; m -- { o p n }; n -- { q r }; "
		+ "o -- { s p }; p -- { s t q }; q -- { t r }; r -- t; s -- z; t -- z; "
		+ "{ rank=same, b, c, d } "
		+ "{ rank=same, e, f, g } "
		+ "{ rank=same, h, i, j, k } "
		+ "{ rank=same, l, m, n } "
		+ "{ rank=same, o, p, q, r } "
		+ "{ rank=same, s, t } "
		+ " }");
	}
}
