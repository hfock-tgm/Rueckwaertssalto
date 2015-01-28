package at.salto.metadaten;

import java.sql.Connection;
import java.util.ArrayList;

/**
 * Interface fuer das Saugverhalten um auch den letzten Rest aus der Datenbank
 * an Metadaten auszusaugen im Strategy Pattern design
 * 
 * @author Hagen Fock 4AHIT
 * @author Michael Weinberger 4AHIT
 * @version 2015-01-28
 * hfock@student.tgm.ac.at
 * mweinberger@student.tgm.ac.at
 *
 */
public interface hooverable {
	/**
	 * Saugt einer Datenbank alle Metadaten aus.
	 * 
	 * @param con
	 *            Die Connection von der Datenbank aus der die Metadaten
	 *            herausgesaugt werden sollen
	 * @param table
	 *            Fuer den Sonderfall des HooverColumn. Damit er weiss aus,
	 *            welcher Tabelle er die Metadaten heraussaugen soll
	 * @return Liefert eine ArrayList<String> in der all die herausgesaugten
	 *         Metadaten, von der Datenbank, sind.
	 * 
	 */
	public ArrayList<String> hooverMetadata(Connection con, String table);
}
