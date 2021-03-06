package at.ac.tgm.fockweinberger.storage;

import java.util.ArrayList;

/**
 * Dieses Objekt bekommt all die Metadaten aus der Datenbank uns speichert sie
 * fuer spaetere Anwendungen
 *
 * @author Hagen Fock 4AHIT hfock@student.tgm.ac.at
 * @version 2015-01-28
 */
public class MetadatenObject {
	private String tableName;
	private ArrayList<String> columns;
	private ArrayList<String> foreignKeys;

	/**
	 * @param tableName Uebergibt den Tabellenamen
	 *
	 */
	public MetadatenObject(String tableName) {
		this.tableName = tableName;
	}

	/**
	 * Fuettert das MetadatenObject mit Metadaten
	 * 
	 * @param columns Holt alle columns die zu der Tabelle gehoeren
	 * @param foreignKeys Holt alle foreignKeys die zu der Tabelle gehoeren
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
			result.append(columns.get(i).substring(tableName.length()+1) + ", ");
		}
		for (int j = 0; j < foreignKeys.size(); j++) {
			result.append(foreignKeys.get(j) + ", ");
		}
		result.delete(result.length() - 2, result.length());
		result.append(")");
		return result.toString();
	}

	@SuppressWarnings("javadoc")
	public String getTableName() {
		return this.tableName;
	}

	@SuppressWarnings("javadoc")
	public ArrayList<String> getColumns() {
		return this.columns;
	}

	@SuppressWarnings("javadoc")
	public ArrayList<String> getForeignKeys() {
		return this.foreignKeys;
	}
}
