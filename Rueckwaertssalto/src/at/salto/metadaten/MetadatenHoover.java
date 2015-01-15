package at.salto.metadaten;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

import at.salto.connection.ConnectDB;
import at.salto.parsen.Commands;

/**
 * @author FOCK
 *
 */
public class MetadatenHoover implements hooverbehaviour {
	private Connection con;
	private ResultSetMetaData rsMetaData;
	private ResultSet rs;
	private Statement st;
	private hooverbehaviour hb;
	private Commands commands;

	/**
	 * @param con 
	 * @param commands 
	 */
	public MetadatenHoover(ConnectDB con, Commands commands) {
		this.con = con.getCon();
		this.commands = commands;
	}

	/**
	 * 
	 */
	public void init() {
		try {
			st = con.createStatement();
			this.rs = st.executeQuery("SELECT * FROM sender");
			this.setRsMetaData(rs.getMetaData());
			

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	/**
	 * @param hb
	 */
	public void chooseBehaviour(hooverbehaviour hb){
		this.hb = hb;	
	}
	/**
	 * Diese Methode fuehrt alle Strategy Algorithmen aus.
	 */
	public void doIt(){
		chooseBehaviour(new HooverColumn());
		hooverMetadata(rsMetaData);
	}

	@Override
	public void hooverMetadata(ResultSetMetaData rsMetaData) {
		this.hb.hooverMetadata(rsMetaData);
		
	}

	/**
	 * @return
	 */
	public ResultSetMetaData getRsMetaData() {
		return rsMetaData;
	}

	/**
	 * @param rsMetaData
	 */
	public void setRsMetaData(ResultSetMetaData rsMetaData) {
		this.rsMetaData = rsMetaData;
	}

}
