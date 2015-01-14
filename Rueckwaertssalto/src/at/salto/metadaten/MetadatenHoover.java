package at.salto.metadaten;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

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
	/**
	 * @param c
	 */
	public MetadatenHoover(ConnectDB c){
		this.con = c.getCon();
	}
	/**
	 * 
	 */
	public void init(){
		try {
			this.st = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
			        ResultSet.CONCUR_UPDATABLE);
			this.rs = st.executeQuery("SELECT * FROM survey");
			this.rsMetaData = rs.getMetaData();
			int numberOfColumns = rsMetaData.getColumnCount();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	/**
	 * 
	 */
	public void hooverColumn(){
		
	}
}
