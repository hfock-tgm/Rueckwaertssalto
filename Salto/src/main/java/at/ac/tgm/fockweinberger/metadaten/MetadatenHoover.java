package at.ac.tgm.fockweinberger.metadaten;

import java.sql.Connection;
import java.sql.ResultSetMetaData;
import java.util.ArrayList;

import at.ac.tgm.fockweinberger.connection.ConnectDB;
import at.ac.tgm.fockweinberger.storage.MetadatenObject;

/**
 * Fuehrt verschiedene Algorithmen aus, um Metadaten aus einer Datenbank zu
 * erhalten.
 *
 * @author Hagen Fock 4AHIT hfock@student.tgm.ac.at
 * @version 2015-01-28
 */
public class MetadatenHoover implements hooverable {
	private Connection con;
	private ResultSetMetaData rsMetaData;
	private hooverable hb;

	private ArrayList<MetadatenObject> storagedObjects;

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
	 * @param hb uebergibt die Hooverklasse
	 */
	public void chooseBehaviour(hooverable hb) {
		this.hb = hb;
	}

	/**
	 * 
	 */
	public void fillObjects() {
		// In diese ArrayList werden all die befuellten MetadatenObjects
		// gespeichert
		this.storagedObjects = new ArrayList<MetadatenObject>();
		// Hilfs ArrayList um die Tablenames zu speichern
		ArrayList<String> helpTable = new ArrayList<String>();
		ArrayList<String> helpColumn = new ArrayList<String>();
		ArrayList<String> helpForeign = new ArrayList<String>();

		// TableArray wird mit all den Tablenames gefuellt
		// Saugverhalten wird ausgesucht ==> Tabellennamen
		chooseBehaviour(new HooverTables());
		helpTable = hooverMetadata(this.con, null);
		// Hier werden alle Tablenames durchgeloopt
		for (int i = 0; i < helpTable.size(); i++) {
			// Saugverhalten wird ausgesucht ==> Tabellennamen
			chooseBehaviour(new HooverColumn());
			helpColumn = hooverMetadata(this.con, helpTable.get(i));
			chooseBehaviour(new HooverForeignKeys());
			helpForeign = hooverMetadata(this.con, helpTable.get(i));

			MetadatenObject TRexDerGrausame = new MetadatenObject(
					helpTable.get(i));
			TRexDerGrausame.takeAllINeed(helpColumn, helpForeign);
			this.storagedObjects.add(TRexDerGrausame);
		}
	}

	/**
	 * 
	 */
	public void testObjects() {
		if (this.storagedObjects == null) {
			System.out
					.println("Die ArrayList, welche fuer Objekte zustaendig ist, ist leer");
		} else {
			for (MetadatenObject o : this.storagedObjects) {
				System.out.println(o.toString());
			}
		}
	}

	/**
	 * 
	 */
	public void test() {
        ArrayList<String> helpTable = new ArrayList<String>();
        ArrayList<String> helpColumn = new ArrayList<String>();
        ArrayList<String> helpForeign = new ArrayList<String>();

        chooseBehaviour(new HooverTables());
        helpTable = hooverMetadata(this.con, null);
        for (int i = 0; i < helpTable.size(); i++) {
            System.out.println(i + " Tabellename: " + helpTable.get(i));
            chooseBehaviour(new HooverColumn());
            helpColumn = hooverMetadata(this.con, helpTable.get(i));
            for (int j = 0; j < helpColumn.size(); j++) {
                System.out.println("	" + j + " Spaltenname: "
                        + helpColumn.get(j));
            }
            chooseBehaviour(new HooverForeignKeys());
            helpForeign = hooverMetadata(this.con, helpTable.get(i));
            for (int k = 0; k < helpForeign.size(); k++)
                System.out.println("	" + k + " ForeignKey:  "
                        + helpForeign.get(k));
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

	@SuppressWarnings("javadoc")
	public ArrayList<MetadatenObject> getObjects() {
		return storagedObjects;
	}
}
