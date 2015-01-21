package at.salto.rm;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.util.ArrayList;

import at.salto.connection.ConnectDB;
import at.salto.metadaten.HooverColumn;
import at.salto.metadaten.HooverTables;
import at.salto.metadaten.MetadatenHoover;
import at.salto.metadaten.MetadatenObject;
import at.salto.metadaten.hooverable;

/**
 * @author FOCK
 *
 */
public class ToRM implements hooverable {
	private MetadatenHoover hoover;
	private PrintWriter out;
	private hooverable hb;
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
	 * Schliesst den StopWriter
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

	/**
	 * Waehlt den Algorithmus aus, welcher angwendet werden soll. Strategy
	 * Pattern
	 * 
	 * @param hb
	 */
	public void chooseBehaviour(hooverable hb) {
		this.hb = hb;
	}

	@Override
	public ArrayList<String> hooverMetadata(Connection con, String table) {
		ArrayList<String> result = this.hb.hooverMetadata(con, table);
		return result;
	}

}
