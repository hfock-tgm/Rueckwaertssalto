package at.salto.metadaten;

import java.util.ArrayList;

/**
 * Dieses Objekt bekommt all die Metadaten aus der Datenbank uns speichert sie
 * fuer spaetere Anwendungen
 * 
 * @author Hagen Fock 4AHIT
 * @author Michael Weinberger 4AHIT
 * @version 2015-01-28
 * hfock@student.tgm.ac.at
 * mweinberger@student.tgm.ac.at
 *
 */
public class MetadatenObject {
	private String tableName;
	private ArrayList<String> columns;
	private ArrayList<String> foreignKeys;

	/**
	 * @param tableName
	 * @param con
	 * 
	 */
	public MetadatenObject(String tableName) {
		this.tableName = tableName;
	}

	/**
	 * Fuettert das MetadatenObject mit Metadaten
	 * 
	 * @param columns
	 * @param foreignKeys
	 */
	public void takeAllINeed(ArrayList<String> columns,
			ArrayList<String> foreignKeys) {
		this.columns = columns;
		this.foreignKeys = foreignKeys;
	}

	@Override
	public String toString() {
		StringBuilder result = new StringBuilder();
		result.append(this.tableName);
		result.append("(");
		for (int i = 0; i < columns.size(); i++) {
			result.append(columns.get(i) + ", ");
		}
		for (int j = 0; j < foreignKeys.size(); j++) {
			result.append(foreignKeys.get(j) + ", ");
		}
		result.delete(result.length() - 2, result.length());
		result.append(")");
		return result.toString();
	}

}
