package at.salto.metadaten;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

import org.h2.engine.SysProperties;

import at.salto.connection.ConnectDB;

/**
 * @author FOCK
 *
 */
public class MetadatenHoover {
	private Connection con;
	private ResultSetMetaData rsMetaData;
	private ResultSet rs;
	private Statement st;
	private int numberOfColumns;

	/**
	 * @param c
	 */
	public MetadatenHoover(ConnectDB c) {
		System.out.println("HALLO");
		this.con = c.getCon();
	}

	/**
	 * 
	 */
	public void init() {
		System.out.println("Hallo");
		try {
//			this.st = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
//					ResultSet.CONCUR_UPDATABLE);
			st = con.createStatement();
			this.rs = st.executeQuery("SELECT * FROM sender");
			this.rsMetaData = rs.getMetaData();
			

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * @throws SQLException
	 * 
	 */
	public void hooverColumn() {
		try {
			this.numberOfColumns = rsMetaData.getColumnCount();
			System.out.println(this.numberOfColumns);
			for (int i = 1; i < this.numberOfColumns + 1; i++) {
				String columnName = rsMetaData.getColumnName(i);
				String tableName = rsMetaData.getTableName(i);
				System.out.println(columnName);
				System.out.println(tableName);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
