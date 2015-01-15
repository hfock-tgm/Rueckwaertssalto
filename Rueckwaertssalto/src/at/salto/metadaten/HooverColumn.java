package at.salto.metadaten;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 * @author FOCK
 *
 */
public class HooverColumn implements hooverbehaviour {
	/**
	 * Diese globale Variable hilft die Ausgabe schoener zu gestallten
	 */
	public static int counter = 0;
	private int numberOfColumns;
	private Statement st;
	private ResultSet rs;
	private ResultSetMetaData rsMetaData;

	@Override
	public ArrayList<String> hooverMetadata(Connection con, String table) {
		ArrayList<String> result = new ArrayList<String>();
		try {
			st = con.createStatement();
			this.rs = st.executeQuery("SELECT * FROM "+table);
			rsMetaData = rs.getMetaData();
			this.numberOfColumns = rsMetaData.getColumnCount();
			for (int i = 1; i < this.numberOfColumns; i++) {
				String columnName = rsMetaData.getColumnName(i);
				String tableName = rsMetaData.getTableName(i);
				counter++;
				System.out.println(counter + ". " + tableName + ": " + columnName);
				result.add(columnName);
				result.add(tableName);
			}
		} catch (SQLException e) {
			System.err.println("Es gab eine SQL Exception bitte ueberpruefen Sie Ihre query");
			e.printStackTrace();
		}
		return result;
		
	}

	
}
