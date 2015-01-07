package at.insy.exporter;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Diese Klasse baut eine Verbindung mit einer MySQL-DB auf, fuehrt Querys und
 * liefert die Ergebnise.
 * 
 * http://www.tutorialspoint.com/jdbc/jdbc-db-connections.htm
 * 
 * @author FOCK
 *
 */
public class Exporter {
	// JDBC driver name and database URL
	static final String JDBC_DRIVER_MYSQL = "com.mysql.jdbc.Driver";
	static final String DB_URL = "jdbc:mysql://localhost:3306/premiere";
	// Database credentials
	static final String USER = "superuser";
	static final String PASS = "1234";

	private Statement stmt = null;
	private String query = null;
	private Connection con = null;
	private ResultSet rs = null;

	private Commands c;

	/**
	 * Der Konstruktor
	 * 
	 * @param c Die Commands Klasse
	 */
	public Exporter(Commands c) {
		this.c = c;
	}

	/**
	 * This method checks if all the required args were set.
	 */
	public void check() {
		if (c.getDb() == null || c.getUser() == null || c.getPass() == null
				|| c.getTable() == null || c.getList() == null) {
			System.out
					.println("Sie haben nicht alle Argumente angegeben, welche benoetigt werden!");
			System.exit(0);
		}
		/*
		 * if (c.getSort() == null && c.getToSort() == null) {
		 * System.out.println("Passt"); } else if (c.getToSort() == null &&
		 * c.getSort() != null || c.getToSort() != null && c.getSort() == null)
		 * { System.out .println(
		 * "Wenn Sie sortieren wollen muessen sie sowohl das Feld als auch die Sortierart angeben"
		 * ); System.exit(0); }
		 */
	}

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
			con = DriverManager.getConnection("jdbc:mysql://" + c.getHost()
					+ "/" + c.getDb(), c.getUser(), c.getPass());

		} catch (ClassNotFoundException e) {
			System.err.println("Driver scheint nicht in Ordnung zu sein!");
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * Diese Methode baut die Query zusammen.
	 */
	public void doQuery() {
		try {
			// STEP 4: Execute a query
			System.out.println("Creating statement...");

			this.stmt = con.createStatement();
//			Wenn man * per Konsolenargumente eingibt wird daraus .classpath
//			deswegen die if Abfrage
			if (c.getList().equals(".classpath"))
				c.setList("*");
//			Hier wird die Query zusammengestellt
			this.query = "SELECT " + c.getList() + " FROM " + c.getTable();
			if (!(c.getWhere() == null))
				this.query += " WHERE " + c.getWhere();
//			Wenn c.getSort && c.getToSort nicht null sind wird der String der Query angehaengt
			if (((c.getSort() != null) && (c.getToSort() != null)))
				this.query += " ORDER BY " + c.getToSort() + " " + c.getSort();
			getResult();
		} catch (SQLException e) {
			System.err.println("Die SQL Query ist nicht in Ordnung!");
			e.printStackTrace();
		}
	}

	/**
	 * Diese Methode liefert die Elemente aus der DB
	 */
	public void getResult() {
		// STEP 5: Extract data from result set
		try {
			if (!(c.getOutput() == null)) {
				File file = new File(c.getOutput() + ".txt");
				FileOutputStream fis = new FileOutputStream(file);
				PrintStream out = new PrintStream(fis);
				System.setOut(out);
				System.out.println(this.query);
			}
			// Fuer den Fall, dass * eingegeben wird brauch ich einen anderen
			// Algorithmus als bei einer normalen Eingabe
			if (c.getList().equals("*")) {
				this.rs = stmt.executeQuery(query);
				while (rs.next()) {
					String ausgabe = null;
					// j = 1 weil das ResultSet bei 1 zu zaehlen beginnt
					for (int j = 1; j <= Integer.parseInt(c.getCount()); j++) {
						ausgabe = rs.getString(j);
						System.out.println(ausgabe + c.getTrenn());
					}
				}
			} else {
				String[] help = c.getList().split(",");
				for (int i = 0; i < help.length; i++) {
					this.rs = stmt.executeQuery(query);
					while (rs.next()) {
						String ausgabe = rs.getString(help[i]);
						System.out.println(ausgabe + c.getTrenn());
					}
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * This method closes all the established connection
	 * 
	 */
	public void disconnect() {
		// Clean-up environment
		try {
			// System.out.println("Disconnecting from database...");
			if (!(rs == null))
				rs.close();
			if (!(stmt == null))
				stmt.close();
			if (!(con == null))
				con.close();
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
	public void connectionTestMySQL() {
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
					"jdbc:mysql://localhost:3306/premiere", "root", "1234");

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
}
