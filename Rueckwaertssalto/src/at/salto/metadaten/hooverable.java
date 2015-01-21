package at.salto.metadaten;

import java.sql.Connection;
import java.util.ArrayList;

/**
 * Interface fuer das Saugverhalten um auch den letzten Rest aus der Datenbank
 * an Metadaten auszusaugen im Strategy Pattern design
 * 
 * @author FOCK
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
