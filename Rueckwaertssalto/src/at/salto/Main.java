package at.salto;

import at.salto.connection.ConnectDB;
import at.salto.parsen.Commands;

/**
 * Main
 * 
 * @author FOCK
 *
 */
public class Main {
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Commands c = new Commands(args);
		ConnectDB db = new ConnectDB(c);
		db.connect();
		db.disconnect();
	}
}
