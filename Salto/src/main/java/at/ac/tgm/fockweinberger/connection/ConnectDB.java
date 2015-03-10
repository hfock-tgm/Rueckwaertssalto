package at.ac.tgm.fockweinberger.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import at.ac.tgm.fockweinberger.parsen.Commands;

/**
 * Verbindung mit einer Datenbank
 * 
 * @author Hagen Fock 4AHIT hfock@student.tgm.ac.at
 * @version 2015-01-28

 */
public class ConnectDB {
	// JDBC driver name
	static final String JDBC_DRIVER_MYSQL = "com.mysql.jdbc.Driver";

	private Connection con = null;

	private Commands c;

	/**
	 * @param c den Commands Klasse, welche all die Argumente geparst hat.
	 */
	public ConnectDB(Commands c) {
		this.c = c;
	}

	/**
	 * Ueberprueft ob alle notwendigen Parameter beim Start des Programmes
	 * angegeben wurden
	 */
	public void check() {
		if (c.getDb() == null || c.getUser() == null || c.getPass() == null) {
			System.out
					.println("Sie haben nicht alle Argumente angegeben, welche benoetigt werden!");
			System.exit(0);
		}
	}

	/**
	 * Verbindet sich mit der Datenbank
	 */
	public void connect() {
		try {
			// Register JDBC driver
			Class.forName(JDBC_DRIVER_MYSQL);
		} catch (ClassNotFoundException e) {
			System.err.println("Where is your MySQL JDBC Driver?");
			e.printStackTrace();
			return;
		}
		System.out.println("MySQL JDBC Driver Registered!");
		try {
			// Open a connection
			System.out.println("Connecting to database...");
			setCon(DriverManager.getConnection("jdbc:mysql://" + c.getHost()
					+ "/" + c.getDb(), c.getUser(), c.getPass()));

		} catch (SQLException e) {
			System.err.println("Connection Failed! Check output console");
			e.printStackTrace();
			return;
		}

		if (con != null) {
			System.out
					.println("Du hast es geschafft! Uebernimm jetzt die Kontrolle ueber deine Datenbank junger Padawan");
		} else {
			System.out.println("Failed to make connection!");
		}
	}

	/**
	 * Schliesst alle Verbindungen
	 * 
	 */
	public void disconnect() {
		// Clean-up environment
		try {
			System.out.println("Disconnecting from database...");
			if (!(con == null))
				con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * @return Connection
	 */
	public Connection getCon() {
		return con;
	}

	/**
	 * @param con die Connection
	 */
	public void setCon(Connection con) {
		this.con = con;
	}
}
