package at.salto;

import at.salto.connection.ConnectDB;
import at.salto.focktesting.EERDot;
import at.salto.metadaten.MetadatenHoover;
//import at.salto.output.ToEER;
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
	 */
	public static void main(String[] args) {
		Commands c = new Commands(args);
		ConnectDB db = new ConnectDB(c);
		db.check();
		db.connect();
		MetadatenHoover hoover = new MetadatenHoover(db);
		// hoover.test();
		hoover.fillObjects();
		// hoover.testObjects();
		ToRM rm = new ToRM(hoover);
		rm.startPrintWriter();
		rm.doRMFile();
		rm.stopPrintWriter();
		// ToEER eer = new ToEER(hoover);
		// eer.startPrintWriter();
		// eer.doDOTFile();
		// eer.stopPrintWriter();
		// eer.toPNG();
		EERDot fock = new EERDot(hoover);
		fock.startPrintWriter();
		fock.babyStepDot();
		// fock.doDOTFile();
		fock.stopPrintWriter();
		fock.toSVG();
		
//		hoover.UNKNOWN();
		
		db.disconnect();
	}
}
