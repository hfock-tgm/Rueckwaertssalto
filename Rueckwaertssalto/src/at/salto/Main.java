package at.salto;

import at.salto.connection.ConnectDB;
import at.salto.metadaten.MetadatenHoover;
import at.salto.output.ToERD;
import at.salto.output.ToRM;
import at.salto.parsen.Commands;

/**
 * Main
 * 
 * @author Hagen Fock 4AHIT & Michael Weinberger 4AHIT
 * @version 2015-01-28
 * @email hfock@student.tgm.ac.at & mweinberger@student.tgm.ac.at
 */
public class Main {
	/**
	 * @param args
	 *            Es muessen folgende Argumente angegeben werden 
	 *            -u User 
	 *            -p Passwort 
	 *            -d Datenbank 
	 *            Zum Beispiel: superuser -p 1234 -d
	 *            testDB
	 */
	public static void main(String[] args) {
		Commands c = new Commands(args);
		ConnectDB db = new ConnectDB(c);
		db.check();
		db.connect();
		MetadatenHoover hoover = new MetadatenHoover(db);
		// hoover.UNKNOWN();
		// hoover.test();
		hoover.fillObjects();
		// hoover.testObjects();
		ToRM rm = new ToRM(hoover);
		rm.startPrintWriter();
		rm.doRMFile();
		rm.stopPrintWriter();
		ToERD eer = new ToERD(hoover);
		eer.startPrintWriter();
		eer.doDOTFile();
		eer.stopPrintWriter();
		eer.toSVG();
		db.disconnect();
	}
}
