package at.salto.storage;

import java.util.ArrayList;

/**
 * Dieses Objekt bekommt all die Metadaten aus der Datenbank uns speichert sie
 * fuer spaetere Anwendungen
 * 
 * @author Hagen Fock 4AHIT & Michael Weinberger 4AHIT
 * @version 2015-01-28
 * @email hfock@student.tgm.ac.at & mweinberger@student.tgm.ac.at
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
	
//	/**
//	 * 
//	 */
//	public void prepareTablesForERD(){
//		this.columns = new ArrayList<String>();
//		for (int i = 0; i < this.columns.size(); i++) {
//			String help = "this.columns.get(i)";
//			if (help.substring(this.columns.get(i).length() - 2, this.columns.get(i).length()) == "PK") {
//				this.columns.get(i) = "{node [label="+ this.columns.get(i) +" decorate=\"true\"][label=<<u>"+  +"</u>>];}";
////				System.out.println("{node [label="id" decorate="true"] idProdukt[label=<id: is NOT NULL>][label=<<u>id</u>>];}");
//			}
//		}
//	}

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
