package at.salto.focktesting;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import at.salto.metadaten.MetadatenHoover;
import at.salto.storage.MetadatenObject;

/**
 * Diese Klasse macht mithilfe des Metadatenobject ein DOT-File und generiert
 * daraus eine SVGrafik
 * 
 * @author Hagen Fock 4AHIT & Michael Weinberger 4AHIT
 * @version 2015-01-28
 * @email hfock@student.tgm.ac.at & mweinberger@student.tgm.ac.at
 */
public class EERDot {
	private PrintWriter out;
	private ArrayList<MetadatenObject> storagedObjects;

	private final static String DOT_EXE_LOCATION = "graphviz-2.38\\release\\bin\\dot.exe";

	/**
	 * @param hoover
	 * 
	 */
	public EERDot(MetadatenHoover hoover) {
		this.storagedObjects = hoover.getObjects();
	}

	/**
	 * oeffnet den PrintWriter
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
	 * Das ist eine Methode um sich ans Ergebnis heran zu tasten
	 */
	public void babyStepDot() {
		out.println("digraph G {");
		for (int i = 0; i < storagedObjects.size(); i++) {
			for (int j = 0; j < this.storagedObjects.get(i).getColumns().size(); j++) {
				out.println(this.storagedObjects.get(i).getTableName() + "->"
						+ this.storagedObjects.get(i).getTableName() + "_"
						+ this.storagedObjects.get(i).getColumns().get(j) + ";");
				for (int k = 0; k < this.storagedObjects.get(i)
						.getForeignKeys().size(); k++) {
					out.println(this.storagedObjects.get(i).getTableName()
							+ "->"
							+ this.storagedObjects.get(i).getForeignKeys()
									.get(k) + ";");
				}
			}

		}
		out.println("}");
	}

	/**
	 * Das ist eine Methode um sich ans Ergebnis heran zu tasten
	 */
	public void babyStepDot2() {
		out.println("digraph G {");
		for (int i = 0; i < storagedObjects.size(); i++) {
			// Hier werden alle Tabellen aufgelistet mit einer [shape=box]
			out.println(storagedObjects.get(i).getTableName().toString()
					+ " [shape=box];");
			// Hier wird jeder Tabelle die dazugehoerigen Spalten hinzugefuegt
			for (int j = 0; j < storagedObjects.get(i).getColumns().size(); j++) {
				out.println(storagedObjects.get(i).getTableName().toString()
						+ "->" + storagedObjects.get(i).getColumns().get(j));
			}
			// Hier wird jeder Tabelle die dazugehoerigen ForeognKeys
			// hinzugefuegt
			for (int k = 0; k < storagedObjects.get(i).getForeignKeys().size(); k++) {
				out.println(storagedObjects.get(i).getTableName().toString()
						+ "->" + storagedObjects.get(i).getForeignKeys().get(k));
			}
		}
		// Diese Variable wird benoetigt damit nicht eine Tabelle oefters auf
		// eine andere Tabelle zeigt
		String help2 = null;
		// Hier werden die Tabellen miteinander "verbunden"
		for (int i = 0; i < storagedObjects.size(); i++) {
			String help = storagedObjects.get(i).getTableName();
			for (int j = 0; j < storagedObjects.get(i).getForeignKeys().size(); j++) {
				// Diese IF-Abfrage ueberprueft ob der Verweis auf eine andere
				// Tabelle schon erstellt wurde
				if (help2 != storagedObjects
						.get(i)
						.getForeignKeys()
						.get(j)
						.substring(
								0,
								storagedObjects.get(i).getForeignKeys().get(j)
										.indexOf("_"))) {
					out.println(help
							+ "->"
							+ storagedObjects
									.get(i)
									.getForeignKeys()
									.get(j)
									.substring(
											0,
											storagedObjects.get(i)
													.getForeignKeys().get(j)
													.indexOf("_")));
					help2 = storagedObjects
							.get(i)
							.getForeignKeys()
							.get(j)
							.substring(
									0,
									storagedObjects.get(i).getForeignKeys()
											.get(j).indexOf("_"));
				}
			}
		}
		out.println("}");
	}

	/**
	 * Macht aus dem DOT-File eine SVGrafik
	 */
	public void toSVG() {
		Runtime rt = Runtime.getRuntime();
		try {
			// Process pr = rt.exec("" + DOT_EXE_LOCATION
			// + " -Tsvg EER.dot -o EER.svg");
			rt.exec("" + DOT_EXE_LOCATION + " -Tsvg EER.dot -o EER.svg");
			System.out.println(DOT_EXE_LOCATION + " -Tsvg EER.dot -o EER.svg");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("SVG wurde erstellt!");
	}
}