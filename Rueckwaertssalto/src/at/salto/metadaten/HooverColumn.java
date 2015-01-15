package at.salto.metadaten;

import java.sql.ResultSetMetaData;
import java.sql.SQLException;

/**
 * @author FOCK
 *
 */
public class HooverColumn implements hooverbehaviour {
	private int numberOfColumns;

	@Override
	public void hooverMetadata(ResultSetMetaData rsMetaData) {
		try {
			this.numberOfColumns = rsMetaData.getColumnCount();
			for (int i = 1; i < this.numberOfColumns; i++) {
				String columnName = rsMetaData.getColumnName(i);
				String tableName = rsMetaData.getTableName(i);
				System.out.println(columnName);
				System.out.println(tableName);
			}
		} catch (SQLException e) {
			System.err.println("Es gab eine SQL Exception bitte ueberpruefen Sie Ihre query");
			e.printStackTrace();
		}
		
	}

	
}
