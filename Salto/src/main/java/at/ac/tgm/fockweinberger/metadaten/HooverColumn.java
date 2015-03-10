package at.ac.tgm.fockweinberger.metadaten;

import java.sql.*;
import java.util.*;

/**
 * Dieser Hoover saugt die Column Namen einer Tabelle aus
 * 
 * @author Michael Weinberger 4AHIT mweinberger@student.tgm.ac.at
 * @version 2015-01-28
 */
public class HooverColumn implements hooverable {

	@Override
	public ArrayList<String> hooverMetadata(Connection con, String table) {
		ArrayList<String> result = new ArrayList<String>();
		try {
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery("SELECT * FROM " + table);
			ResultSetMetaData rsMetaData = rs.getMetaData();
//			Hier wird der Column count herausgelesen
			int numberOfColumns = rsMetaData.getColumnCount();

//			Hier werden die Primary keys ausgelesen
			DatabaseMetaData meta = con.getMetaData();
			ResultSet pk = meta.getPrimaryKeys(null, null, table);

			ArrayList<String> primary = new ArrayList<String>();

			while (pk.next()) {
				primary.add(pk.getString("COLUMN_NAME"));
			}

			for (int i = 1; i < numberOfColumns; i++) {
				String columnName = rsMetaData.getColumnName(i);
				String isNull = "_NN";
				Iterator<String> it = primary.iterator();
				while (it.hasNext()) {
					if (columnName.equals(it.next()) == true) {
						columnName = columnName + "_PK";
					}
				}
				if (rsMetaData.isNullable(i) == 0) columnName += isNull;
				columnName = table + "_" + columnName;
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
