package at.salto.metadaten;

import java.sql.Connection;
import java.util.ArrayList;

import at.salto.connection.ConnectDB;

/**
 * Dieses Objekt bekommt all die Metadaten aus der Datenbank uns speichert sie
 * fuer spaetere Anwendungen
 * 
 * @author FOCK
 *
 */
public class MetadatenObject implements hooverable {
	private hooverable hb;
	private String tableName;
	private ArrayList<String> columns;
	private Connection con;

	/**
	 * @param tableName
	 * @param con
	 * 
	 */
	public MetadatenObject(String tableName, ConnectDB con) {
		this.tableName = tableName;
		this.con = con.getCon();
	}

	/**
	 * Fuettert das MetadatenObject mit Metadaten
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

	/**
	 * Waehlt den Algorithmus aus, welcher angwendet werden soll. Strategy
	 * Pattern
	 * 
	 * @param hb
	 */
	public void chooseBehaviour(hooverable hb) {
		this.hb = hb;
	}

	/**
	 * Waehlt den Algorithmus aus, welcher angwendet werden soll. Strategy
	 * Pattern
	 * 
	 * @param hb
	 */
	@Override
	public ArrayList<String> hooverMetadata(Connection con, String table) {
		ArrayList<String> result = this.hb.hooverMetadata(con, table);
		return result;
	}

}
