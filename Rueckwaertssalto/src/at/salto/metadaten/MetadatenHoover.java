package at.salto.metadaten;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import java.util.ArrayList;

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
	 * @param hb
	 */
	public void chooseBehaviour(hooverbehaviour hb){
		this.hb = hb;	
	}
	/**
	 * Diese Methode fuehrt alle Strategy Algorithmen aus.
	 */
	public void doIt(){
		ArrayList<String> help = new ArrayList<String>();
		chooseBehaviour(new HooverTables());
		help = hooverMetadata(this.con, null);
		System.out.println("-------------------");
		chooseBehaviour(new HooverColumn());
		for (int i = 0; i < help.size(); i++) {
			hooverMetadata(this.con, help.get(i));
		}
	}

	@Override
	public ArrayList<String> hooverMetadata(Connection con, String table) {
		ArrayList<String> result = this.hb.hooverMetadata(con, table);
		return result;
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
