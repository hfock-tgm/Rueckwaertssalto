package at.salto.metadaten;

import java.sql.Connection;
import java.sql.ResultSetMetaData;
import java.util.ArrayList;

import at.salto.connection.ConnectDB;

/**
 * Fuehrt verschiedene Algorithmen aus, um Metadaten aus einer Datenbank zu
 * erhalten.
 * 
 * @author FOCK
 *
 */
public class MetadatenHoover implements hooverbehaviour {
	private Connection con;
	private ResultSetMetaData rsMetaData;
	private hooverbehaviour hb;

	/**
	 * Konstruktor
	 * 
	 * @param con
	 *            Die Datenbank aus der die Metadaten herausgesaugt werden
	 *            sollen
	 */
	public MetadatenHoover(ConnectDB con) {
		this.con = con.getCon();
	}

	/**
	 * Waehlt den Algorithmus aus, welcher angwendet werden soll. Strategy
	 * Pattern
	 * 
	 * @param hb
	 */
	public void chooseBehaviour(hooverbehaviour hb) {
		this.hb = hb;
	}

	/**
	 * Diese Methode fuehrt alle Strategy Algorithmen aus.
	 */
	public void doIt() {
		ArrayList<String> help = new ArrayList<String>();
		chooseBehaviour(new HooverTables());
		help = hooverMetadata(this.con, null);
		System.out.println("-------------------");
		chooseBehaviour(new HooverColumn());
		for (int i = 0; i < help.size(); i++) {
			hooverMetadata(this.con, help.get(i));
		}
	}

	@Override
	public ArrayList<String> hooverMetadata(Connection con, String table) {
		ArrayList<String> result = this.hb.hooverMetadata(con, table);
		return result;
	}

	@SuppressWarnings("javadoc")
	public ResultSetMetaData getRsMetaData() {
		return rsMetaData;
	}

	@SuppressWarnings("javadoc")
	public void setRsMetaData(ResultSetMetaData rsMetaData) {
		this.rsMetaData = rsMetaData;
	}

}
