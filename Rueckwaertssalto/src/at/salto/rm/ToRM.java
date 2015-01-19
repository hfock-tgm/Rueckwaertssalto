package at.salto.rm;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import at.salto.metadaten.HooverColumn;
import at.salto.metadaten.HooverTables;
import at.salto.metadaten.MetadatenHoover;

/**
 * @author FOCK
 *
 */
public class ToRM {
	private MetadatenHoover hoover;
	private PrintWriter out;

	/**
	 * @param hoover
	 */
	public ToRM(MetadatenHoover hoover) {
		this.hoover = hoover;
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
			out.close();
			System.out.println("PrintWriter closed");
		} else {
			System.out.println("PrintWriter not open");
		}
	}
	/**
	 * Produziert das RM-File
	 */
	public void doRMFile(){
//		hoover.chooseBehaviour();
//		ArrayList<String> help = new ArrayList<String>();
//		hoover.chooseBehaviour(new HooverTables());
//		help = hooverMetadata(hoover.getConnection(), null);
//		System.out.println("-------------------");
//		chooseBehaviour(new HooverColumn());
//		for (int i = 0; i < help.size(); i++) {
//			hooverMetadata(this.con, help.get(i));
//		}
	}
	
}
