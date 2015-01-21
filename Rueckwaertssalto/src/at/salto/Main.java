package at.salto;

import at.salto.connection.ConnectDB;
import at.salto.metadaten.MetadatenHoover;
import at.salto.parsen.Commands;
import at.salto.rm.ToRM;

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
		db.check();
		db.connect();
		MetadatenHoover hoover = new MetadatenHoover(db);
		hoover.test();
//		hoover.fillObjects();
//		hoover.testObjects();
//		ToRM rm = new ToRM(hoover);
//		rm.startPrintWriter();
//		rm.doRMFile();
//		rm.stopPrintWriter();
		db.disconnect();
	}
}
