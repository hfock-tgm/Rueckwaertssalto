package at.ac.tgm.fockweinberger.metadaten;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Saugt die Tabllennamen aus der Datenbank
 * 
 * @author Michael Weinberger 4AHIT mweinberger@student.tgm.ac.at
 * @version 2015-01-28
 */
public class HooverTables implements hooverable {

	@Override
	public ArrayList<String> hooverMetadata(Connection con, String table) {
		DatabaseMetaData md;
		ArrayList<String> result = new ArrayList<String>();
		try {
			md = con.getMetaData();
			ResultSet rs = md.getTables(null, null, "%", null);
			while (rs.next()) {
				result.add(rs.getString(3));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}
}
