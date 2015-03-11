package at.ac.tgm.fockweinberger;

import at.ac.tgm.fockweinberger.connection.ConnectDB;
import at.ac.tgm.fockweinberger.metadaten.MetadatenHoover;
import at.ac.tgm.fockweinberger.output.ToERD;
import at.ac.tgm.fockweinberger.output.ToRM;
import at.ac.tgm.fockweinberger.parsen.Commands;

/**
 * Main
 * 
 * @author Michael Weinberger 4AHIT mweinberger@student.tgm.ac.at
 * @version 2015-01-28
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
		hoover.fillObjects();
		ToRM rm = new ToRM(hoover);
		rm.startPrintWriter();
		rm.doRMFile();
		rm.stopPrintWriter();
		ToERD erd = new ToERD(hoover);
        erd.startPrintWriter();
        erd.doDOTFile();
        erd.stopPrintWriter();
        erd.toSVG();
		db.disconnect();
	}
}
