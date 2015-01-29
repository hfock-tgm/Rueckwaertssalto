package at.salto.output;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import at.salto.metadaten.MetadatenHoover;
import at.salto.metadaten.hooverable;
import at.salto.storage.MetadatenObject;

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
	
	private final static String DOT_EXE_LOCATION = "graphviz-2.38\\release\\bin\\dot.exe";

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
	public void doDOTFile(){
		out.println();
		/*
		out.println("graph { \n"
		+ "rankdir=LR; \n"
		+ "a -- { b c d }; b -- { c e }; c -- { e f }; d -- { f g }; e -- h; \n"
		+ "f -- { h i j g }; g -- k; h -- { o l }; i -- { l m j }; j -- { m n k }; \n"
		+ "k -- { n r }; l -- { o m }; m -- { o p n }; n -- { q r }; \n"
		+ "o -- { s p }; p -- { s t q }; q -- { t r }; r -- t; s -- z; t -- z; \n"
		+ "{ rank=same, b, c, d } \n"
		+ "{ rank=same, e, f, g } \n"
		+ "{ rank=same, h, i, j, k } \n"
		+ "{ rank=same, l, m, n } \n"
		+ "{ rank=same, o, p, q, r } \n"
		+ "{ rank=same, s, t } \n"
		+ " } \n");
		*/
	}
	/**
	 * ACHTUNG FUNKTIONIERT NUR BEI MIR
	 */
	public void toPNG() {
		Runtime rt = Runtime.getRuntime();
		try {
			Process pr = rt.exec(DOT_EXE_LOCATION+" -Tpng EER.dot -o EER.png");
			System.out.println(DOT_EXE_LOCATION+" -Tpng EER.dot -o EER.png");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("PNG wurde erstellt!");
	}
}
