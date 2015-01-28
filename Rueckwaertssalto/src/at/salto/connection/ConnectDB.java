package at.salto.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import at.salto.parsen.Commands;

/**
 * Eine Verbindung zur Datenbank wird hergestellt
 * 
 * @author Hagen Fock 4AHIT
 * @author Michael Weinberger 4AHIT
 * @version 2015-01-28
 * hfock@student.tgm.ac.at
 * mweinberger@student.tgm.ac.at
 *
 */
public class ConnectDB {
	// JDBC driver name
	static final String JDBC_DRIVER_MYSQL = "com.mysql.jdbc.Driver";

	private Connection con = null;

	private Commands c;

	/**
	 * @param c
	 */
	public ConnectDB(at.salto.parsen.Commands c) {
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
	 * @return
	 */
	public Connection getCon() {
		return con;
	}

	/**
	 * @param con
	 */
	public void setCon(Connection con) {
		this.con = con;
	}
}
