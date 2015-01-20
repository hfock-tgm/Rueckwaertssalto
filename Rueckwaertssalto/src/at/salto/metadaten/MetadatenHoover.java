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
public class MetadatenHoover implements hooverable {
	private Connection con;
	private ResultSetMetaData rsMetaData;
	private hooverable hb;

	/**
	 * Konstruktor
	 * 
	 * @param con
	 *            Die Datenbank aus der die Metadaten herausgesaugt werden
	 *            sollen
	 * @param guy
	 *            Der SupportGuy, welcher mit all den Metadaten gefuettert wird,
	 *            fuer leichteres Arbeiten
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
	public void chooseBehaviour(hooverable hb) {
		this.hb = hb;
	}
	@SuppressWarnings("javadoc")
	public void test(){
		chooseBehaviour(new HooverTables());
		hooverMetadata(this.con, null);
	}
	/**
	 * Diese Methode fuehrt alle Strategy Algorithmen aus.
	 */
	public void doIt() {
		ArrayList<String> helpTable = new ArrayList<String>();
		ArrayList<String> helpColumn = new ArrayList<String>();
		chooseBehaviour(new HooverTables());
		helpTable = hooverMetadata(this.con, null);
		for (int i = 0; i < helpTable.size(); i++) {
			System.out.println(i + ". Tablename: " + helpTable.get(i));
			chooseBehaviour(new HooverColumn());
			helpColumn = hooverMetadata(this.con, helpTable.get(i));
			for (int j = 0; j < helpColumn.size(); j++) {
				System.out.println("	"+ j +". Spaltenname: " + helpColumn.get(j));
			}
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

	@SuppressWarnings("javadoc")
	public Connection getConnection() {
		return this.con;
	}

}
