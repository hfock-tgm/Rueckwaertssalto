package at.salto.metadaten;

import java.sql.Connection;
import java.sql.ResultSetMetaData;
import java.util.ArrayList;

import at.salto.connection.ConnectDB;

/**
 * Fuehrt verschiedene Algorithmen aus, um Metadaten aus einer Datenbank zu
 * erhalten.
 * 
 * @author FOCK Hagen Aad hagen.fock@gmail.com
 *
 */
public class MetadatenHoover implements hooverable {
	private ConnectDB connectDB;
	private Connection con;
	private ResultSetMetaData rsMetaData;
	private hooverable hb;

	private ArrayList<MetadatenObject> storageObjects;

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
		this.connectDB = con;
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
	public void test() {
		chooseBehaviour(new HooverTables());
		hooverMetadata(this.con, null);
	}

	/**
	 * 
	 */
	public void fillObjects() {
		// In diese ArrayList werden all die befuellten MetadatenObjects
		// gespeichert
		this.storageObjects = new ArrayList<MetadatenObject>();
		// Hilfs ArrayList um die Tablenames zu speichern
		ArrayList<String> helpTable = new ArrayList<String>();
		ArrayList<String> helpColumn = new ArrayList<String>();

		chooseBehaviour(new HooverTables());
		helpTable = hooverMetadata(this.con, null);
		for (int i = 0; i < helpTable.size(); i++) {
			chooseBehaviour(new HooverColumn());
			helpColumn = hooverMetadata(this.con, helpTable.get(i));
			MetadatenObject test = new MetadatenObject(helpTable.get(i),
					this.connectDB);
			test.takeColumns(helpColumn);
			this.storageObjects.add(test);
		}
	}

	/**
	 * 
	 */
	public void testObjects() {
		if (this.storageObjects == null) {
			System.out
					.println("Die ArrayList, welche fuer Objekte zustaendig ist, ist leer");
		} else {
			for (MetadatenObject o : this.storageObjects) {
				System.out.println(o.toString());
			}
		}
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
			// System.out.println(i + ". Tablename: " + helpTable.get(i));
			chooseBehaviour(new HooverColumn());
			helpColumn = hooverMetadata(this.con, helpTable.get(i));
			for (int j = 0; j < helpColumn.size(); j++) {
				// System.out.println("	"+ j +". Spaltenname: " +
				// helpColumn.get(j));
			}
		}
	}

	/**
	 * 
	 */
	/*
	 * public void doItProper() { ArrayList<String> helpTable = new
	 * ArrayList<String>(); chooseBehaviour(new HooverTables()); helpTable =
	 * hooverMetadata(this.con, null); for (int i = 0; i < helpTable.size();
	 * i++) { MetadatenObject object = new MetadatenObject(helpTable.get(i),
	 * this.connectDB); object.hoovMetadaten();
	 * System.out.println(object.toString()); } }
	 */

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
