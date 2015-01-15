package at.salto.metadaten;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 * Dieser Hoover saugt die Column Namen einer Tabelle aus
 * 
 * @author FOCK
 *
 */
public class HooverColumn implements hooverbehaviour {
	/**
	 * Diese globale Variable hilft die Ausgabe schoener zu gestallten Sie ist
	 * deshalb global da die Methode oefters aufgerufen werden muss damit man
	 * all die Tabellennamen hat und nicht immer bei 1 beginnen muss
	 */
	public static int counter = 0;

	@Override
	public ArrayList<String> hooverMetadata(Connection con, String table) {
		ArrayList<String> result = new ArrayList<String>();
		try {
			Statement st = con.createStatement();
			// Fuehrt die query aus und uebergibt es dem ResultSet
			ResultSet rs = st.executeQuery("SELECT * FROM " + table);
			ResultSetMetaData rsMetaData = rs.getMetaData();
			int numberOfColumns = rsMetaData.getColumnCount();
			for (int i = 1; i < numberOfColumns; i++) {
				String columnName = rsMetaData.getColumnName(i);
				String tableName = rsMetaData.getTableName(i);
				counter++;
				System.out.println(counter + ". " + tableName + ": "
						+ columnName);
				result.add(columnName);
			}
		} catch (SQLException e) {
			System.err
					.println("Es gab eine SQL Exception bitte ueberpruefen Sie Ihre query");
			e.printStackTrace();
		}
		return result;

	}

}
