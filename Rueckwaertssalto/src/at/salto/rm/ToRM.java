package at.salto.rm;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
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
public class ToRM {
	private MetadatenHoover hoover;
	private PrintWriter out;
	private ArrayList<MetadatenObject> storagedObjects;

	/**
	 * @param db
	 * @param hoover
	 */
	public ToRM(MetadatenHoover hoover) {
		this.hoover = hoover;
		this.storagedObjects = hoover.getObjects();
	}

	/**
	 * 
	 */
	public void startPrintWriter() {
		try {
			System.out.println("Starts the PrintWriter");

			out = new PrintWriter(new FileWriter("RM.txt"));

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
	 * Produziert das RM-File
	 */
	public void doRMFile() {
		if (this.storagedObjects == null) {
			System.out
					.println("Die ArrayList, welche fuer Objekte zustaendig ist, ist leer");
		} else {
			for (MetadatenObject o : this.storagedObjects) {
				System.out.println(o.toString());
				out.println(o.toString());
			}
		}
	}

}
