package at.ac.tgm.fockweinberger.output;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import at.ac.tgm.fockweinberger.metadaten.MetadatenHoover;
import at.ac.tgm.fockweinberger.storage.MetadatenObject;

/**
 * Macht mithilfe des MetadatenObject ein RM
 * 
 * @author Michael Weinberger 4AHIT mweinberger@student.tgm.ac.at
 * @version 2015-01-28
 */
public class ToRM {
	private PrintWriter out;
	private ArrayList<MetadatenObject> storagedObjects;

	/**
	 * @param hoover Uebergibt den MetadatenHoover um an die storagedObjects heranzukommen
	 */
	public ToRM(MetadatenHoover hoover) {
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
			out.println("Hinweise zum RM");
			out.println("Primary Keys werden mit _PK gekennzeichnet");
			out.println("NotNull wird mit _NN gekennzeichnet");
			out.println("Der Tabellename wird zu jedem dazugehoerigen Attribut hinzugeschrieben");
			out.println("");
			for (MetadatenObject o : this.storagedObjects) {
				// System.out.println(o.toString());
				out.println(o.toString());
			}
		}
	}

}
