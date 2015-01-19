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
import at.salto.metadaten.hooverbehaviour;

/**
 * @author FOCK
 *
 */
public class ToRM implements hooverbehaviour {
	private MetadatenHoover hoover;
	private PrintWriter out;
	private hooverbehaviour hb;
	private ConnectDB db;

	/**
	 * @param db
	 * @param hoover
	 */
	public ToRM(ConnectDB db) {
		this.db = db;
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
		ArrayList<String> helpTable = new ArrayList<String>();
		ArrayList<String> helpColumn = new ArrayList<String>();
		chooseBehaviour(new HooverTables());
		helpTable = hooverMetadata(db.getCon(), null);
		for (int i = 0; i < helpTable.size(); i++) {
			out.println(i + ". Tablename: " + helpTable.get(i));
			chooseBehaviour(new HooverColumn());
			helpColumn = hooverMetadata(db.getCon(), helpTable.get(i));
			for (int j = 0; j < helpColumn.size(); j++) {
				out.println("	" + j + ". Spaltenname: " + helpColumn.get(j));
			}
		}
	}

	/**
	 * Waehlt den Algorithmus aus, welcher angwendet werden soll. Strategy
	 * Pattern
	 * 
	 * @param hb
	 */
	public void chooseBehaviour(hooverbehaviour hb) {
		this.hb = hb;
	}

	@Override
	public ArrayList<String> hooverMetadata(Connection con, String table) {
		ArrayList<String> result = this.hb.hooverMetadata(con, table);
		return result;
	}

}
