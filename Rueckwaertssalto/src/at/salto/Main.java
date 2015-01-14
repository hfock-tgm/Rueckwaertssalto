package at.salto;

import at.salto.connection.ConnectDB;
import at.salto.metadaten.MetadatenHoover;
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
		MetadatenHoover mh = new MetadatenHoover(db);
		mh.init();
		mh.hooverColumn();
//		db.disconnect();
	}
}
