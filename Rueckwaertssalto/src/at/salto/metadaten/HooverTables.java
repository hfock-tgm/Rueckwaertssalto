package at.salto.metadaten;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * @author FOCK
 *
 */
public class HooverTables implements hooverbehaviour {

	@Override
	public ArrayList<String> hooverMetadata(Connection con, String table) {
		DatabaseMetaData md;
		ArrayList<String> result = new ArrayList<String>();
		int counterLocal = 0;
		try {
			md = con.getMetaData();

			ResultSet rs = md.getTables(null, null, "%", null);
			while (rs.next()) {
				++counterLocal;
				System.out.println(counterLocal + ". Table" + rs.getString(3));
				result.add(rs.getString(3));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}
}
