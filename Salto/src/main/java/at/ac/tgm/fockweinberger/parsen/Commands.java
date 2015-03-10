package at.ac.tgm.fockweinberger.parsen;

import org.apache.commons.cli.BasicParser;
import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.OptionBuilder;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;

/**
 * Diese Klasse liesst mithilfe von der Apache CLI die angegeben Argumente aus
 * 
 * @author Hagen Fock 4AHIT hfock@student.tgm.ac.at
 * @version 2015-01-28
 */
public class Commands {
	private Options options;
	private CommandLineParser parser;
	private CommandLine cmd;
	private HelpFormatter help;

	// needed for the Exporter
	private String host = "localhost";
	private String user = null;
	private String pass = null;
	private String db = null;

	/**
	 * Der Konstruktor braucht die args von der MainKlasse
	 * 
	 * @param args
	 *            Kommandoline input
	 * 
	 */
	public Commands(String[] args) {
		this.options = new Options();
		this.help = new HelpFormatter();
		init();
		this.parser = new BasicParser();
		try {
			this.cmd = parser.parse(options, args);
		} catch (ParseException exp) {
			help.printHelp("The Exporter", options);
			System.err.println("Parsing failed.  Reason: " + exp.getMessage());
		}
		pars();

	}

	/**
	 * In dieser Methode werden alle Optionen definiert
	 */
	@SuppressWarnings({ "static-access" })
	public void init() {
		this.options.addOption(OptionBuilder.withLongOpt("help")
				.withDescription("-? ... Help!?\n").create("?"));
		this.options.addOption(OptionBuilder
				.withLongOpt("host")
				.withDescription(
						"-h ... Hostname des DBMS. Standard: localhost\n")
				.withValueSeparator(' ').hasArg().create("h"));
		this.options
				.addOption(OptionBuilder
						.withLongOpt("user")
						.withDescription(
								"-u ...Benutzername. Standard: Benutzername des im Betriebssystem angemeldeten Benutzers\n")
						.withValueSeparator(' ').hasArg().create("u"));
		this.options
				.addOption(OptionBuilder
						.withLongOpt("password")
						.withDescription(
								"-p ... Passwort. Alternativ kann ein Passwortprompt angezeigt werden. \n")
						.withValueSeparator(' ').hasArg().create("p"));
		this.options.addOption(OptionBuilder.withLongOpt("database")
				.withDescription("-d ... Name der Datenbank")
				.withValueSeparator(' ').hasArg().create("d"));
	}

	/**
	 * In dieser Methode sind die if Unterscheidungen, um die angegebenen
	 * Arguemente auszuwerten. Dann wird ein String von NULL auf den eingesetzen
	 * Wert geaendert und ein anderes Programm ueberprueft mit Hilfe der Getter
	 * ob die Werte z.B. nicht NULL sind.
	 * 
	 */
	public void pars() {
		if (cmd.hasOption("?") || cmd.hasOption("help")) {
			help.printHelp("The Exporter", options);
		}
		if (cmd.hasOption("h") || cmd.hasOption("host")) {
			this.setHost(cmd.getOptionValue("h"));
			// System.out.println(cmd.getOptionValue("h"));
		}
		if (cmd.hasOption("u") || cmd.hasOption("user")) {
			this.setUser(cmd.getOptionValue("u"));
			// System.out.println(cmd.getOptionValue("u"));
		}
		if (cmd.hasOption("p") || cmd.hasOption("password")) {
			this.setPass(cmd.getOptionValue("p"));
			// System.out.println(cmd.getOptionValue("p"));
		}
		if (cmd.hasOption("d") || cmd.hasOption("database")) {
			this.setDb(cmd.getOptionValue("d"));
			// System.out.println(cmd.getOptionValue("d"));
		}
	}

	@SuppressWarnings("javadoc")
	public String getUser() {
		return user;
	}

	@SuppressWarnings("javadoc")
	public void setUser(String user) {
		this.user = user;
	}

	@SuppressWarnings("javadoc")
	public String getPass() {
		return pass;
	}

	@SuppressWarnings("javadoc")
	public void setPass(String pass) {
		this.pass = pass;
	}

	@SuppressWarnings("javadoc")
	public String getDb() {
		return db;
	}

	@SuppressWarnings("javadoc")
	public void setDb(String db) {
		this.db = db;
	}

	@SuppressWarnings("javadoc")
	public String getHost() {
		return host;
	}

	@SuppressWarnings("javadoc")
	public void setHost(String host) {
		this.host = host;
	}
}
