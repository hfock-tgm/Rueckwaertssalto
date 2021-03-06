package at.ac.tgm.fockweinberger.metadaten;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Saugt die ForeigKeys sowie auch den Tabellennamen von den FKs
 * 
 * @author Hagen Fock 4AHIT hfock@student.tgm.ac.at
 * @version 2015-01-28
 */
public class HooverForeignKeys implements hooverable {

	@Override
	public ArrayList<String> hooverMetadata(Connection con, String table) {
		ArrayList<String> result = new ArrayList<String>();
		ArrayList<String> fktable = new ArrayList<String>();
		ArrayList<String> fkcolumn = new ArrayList<String>();
		ResultSet rs = null;
		DatabaseMetaData meta;
		try {
			meta = con.getMetaData();

			// The Oracle database stores its table names as Upper-Case,
			// if you pass a table name in lowercase characters, it will not
			// work.
			// MySQL database does not care if table name is
			// uppercase/lowercase.
			//
			rs = meta.getExportedKeys(con.getCatalog(), null, table);
			while (rs.next()) {
				String fkTableName = rs.getString("FKTABLE_NAME");
				String fkColumnName = rs.getString("FKCOLUMN_NAME");
				// int fkSequence = rs.getInt("KEY_SEQ");
				// System.out.println("getExportedKeys(): fkTableName="
				// + fkTableName);
				// System.out.println("getExportedKeys(): fkColumnName="
				// + fkColumnName);
				// System.out.println("getExportedKeys(): fkSequence="
				// + fkSequence);
				fktable.add(fkTableName);
				fkcolumn.add(fkColumnName);
				result.add(fkTableName + "_" + fkColumnName);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}

}
