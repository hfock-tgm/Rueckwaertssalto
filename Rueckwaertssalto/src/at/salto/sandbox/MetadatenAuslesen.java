package at.salto.sandbox;

import java.sql.*;

/**
 * 
 * 
 * 
 * @author Hagen Fock & Michael Weinberger 4AHITT
 * @version 2015-01-07
 *
 */
public class MetadatenAuslesen {
	// JDBC driver name and database URL
	static final String JDBC_DRIVER_MYSQL = "com.mysql.jdbc.Driver";
	static final String DB_URL = "jdbc:mysql://localhost:3306/premiere";
	// Database credentials
	static final String USER = "insy4";
	static final String PASS = "blabla";

	private Statement stmt = null;
	private String query = null;
	private Connection con = null;
	private ResultSet rs = null;
	
	/**
	 * This method connects to the db.
	 * 
	 */
	public void connect() {
		try {
			// STEP 2: Register JDBC driver
			Class.forName(JDBC_DRIVER_MYSQL);
			// STEP 3: Open a connection
			System.out.println("Connecting to database...");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/premiere");
			
		} catch (ClassNotFoundException e) {
			System.err.println("Driver scheint nicht in Ordnung zu sein!");
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * Diese Methode ermittelt ob man sich mit der MySQL Datenbank verbinden
	 * kann. Falls es nicht der Fall ist liefert sie gute Hinweise was man
	 * eventuell falsch gemacht hat!
	 */
	public static void connectionTestMySQL() {
		System.out
				.println("-------- MySQL JDBC Connection Testing ------------");

		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			System.out.println("Where is your MySQL JDBC Driver?");
			e.printStackTrace();
			return;
		}

		System.out.println("MySQL JDBC Driver Registered!");
		Connection connection = null;

		try {
			connection = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/premiere", "insy4", "blabla");

		} catch (SQLException e) {
			System.out.println("Connection Failed! Check output console");
			e.printStackTrace();
			return;
		}

		if (connection != null) {
			System.out.println("You made it, take control your database now!");
		} else {
			System.out.println("Failed to make connection!");
		}
	}
	
	public static void main(String[] args) {
		
	}
}
