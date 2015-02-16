package at.salto.metadaten;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 * @author Hagen Fock 4AHIT & Michael Weinberger 4AHIT
 * @version 2015-01-28
 * @email hfock@student.tgm.ac.at & mweinberger@student.tgm.ac.at
 */
public class HooverUNKNOWN implements hooverable {

	@Override
	public ArrayList<String> hooverMetadata(Connection con, String table) {
		ArrayList<String> result = new ArrayList<String>();
		try {
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery("SELECT * FROM " + table);
			ResultSetMetaData rsmd;

			rsmd = rs.getMetaData();

			int numberOfColumns = rsmd.getColumnCount();
			for (int i = 1; i <= numberOfColumns; i++) {
				String colName = rsmd.getColumnName(i);
				String tableName = rsmd.getTableName(i);
				String name = rsmd.getColumnTypeName(i);
				boolean caseSen = rsmd.isCaseSensitive(i);
				boolean writable = rsmd.isWritable(i);
				int nullable = rsmd.isNullable(i);
				System.out.println("Information for column " + colName);
				System.out.println("    Column is in table " + tableName);
				System.out.println("    DBMS name for type is " + name);
				System.out.println("    Is case sensitive:  " + caseSen);
				System.out.println("    Is possibly writable:  " + writable);
				String isNull = "NOTnullable";
				if (nullable == 1) isNull = "nullable";
				System.out.println("    Is nullable:  " + isNull);
				System.out.println("");
			}

			while (rs.next()) {
				for (int i = 1; i <= numberOfColumns; i++) {
					String s = rs.getString(i);
					System.out.print(s + "  ");
				}
				System.out.println("");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}
}
