package at.salto.metadaten;

import java.util.ArrayList;

/**
 * Dieses Objekt bekommt all die Metadaten aus der Datenbank uns speichert sie
 * fuer spaetere Anwendungen
 * 
 * @author FOCK
 *
 */
public class MetadatenObject {
	private String tableName;
	private ArrayList<String> columns;

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
	 * @param columns 
	 */
	public void takeColumns(ArrayList<String> columns) {
		this.columns = columns;
	}

	@Override
	public String toString() {
		StringBuilder result = new StringBuilder();
		result.append(this.tableName);
		result.append("(");
		for (int i = 0; i < columns.size(); i++) {
			result.append(columns.get(i) + " ,");
		}
		result.delete(result.length() - 2, result.length());
		result.append(")");
		return result.toString();
	}
}
