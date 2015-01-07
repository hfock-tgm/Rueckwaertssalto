package at.insy.exporter;

import org.apache.commons.cli.BasicParser;
import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.OptionBuilder;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;

/**
 * Diese Klasse ließt mithilfe von der Apache CLI die angegeben Argumente aus
 * 
 * @author FOCK
 *
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
	private String table = null;
	private String toSort = null;
	private String sort = "ASC";
	private String where = null;
	private String list = null;
	private String outputData = null;
	private String trenn = ";";
	private String count = null;
	private String output = null;

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
		this.options
				.addOption(OptionBuilder
						.withLongOpt("sort")
						.withDescription(
								"-s ... Feld, nach dem sortiert werden soll (nur eines möglich, Standard: keines)")
						.withValueSeparator(' ').hasArg().create("s"));
		this.options.addOption(OptionBuilder.withLongOpt("sortASC")
				.withDescription("-r ... Sortierrichtung. Standard: ASC")
				.withValueSeparator(' ').hasArg().create("r"));
		this.options
				.addOption(OptionBuilder
						.withLongOpt("filtern")
						.withDescription(
								"-w ... eine Bedingung in SQL-Syntax, die um Filtern der Tabelle verwendet wird. Standard: keine")
						.withValueSeparator(' ').hasArg().create("w"));
		this.options
				.addOption(OptionBuilder
						.withLongOpt("trenn")
						.withDescription(
								"-t ... Trennzeichen, dass für die Ausgabe verwendet werden soll. Standard: ; ")
						.withValueSeparator(' ').hasArg().create("t"));
		this.options
				.addOption(OptionBuilder
						.withLongOpt("trennf")
						.withDescription(
								"-f ... Kommagetrennte Liste (ohne Leerzeichen) der Felder, die im Ergebnis enthalten sein sollen. * soll akzeptiert werden (Pflicht)")
						.withValueSeparator(' ').hasArg().create("f"));
		this.options
				.addOption(OptionBuilder
						.withLongOpt("output")
						.withDescription(
								"-o ... Name der Ausgabedatei. Standard: keine -> Ausgabe auf der Konsole")
						.withValueSeparator(' ').hasArg().create("o"));
		this.options.addOption(OptionBuilder.withLongOpt("table")
				.withDescription("-T ... Tabellenname (Pflicht)")
				.withValueSeparator(' ').hasArg().create("T"));
		this.options
				.addOption(OptionBuilder
						.withLongOpt("tableCount")
						.withDescription(
								"-c ... Die Anzahl der Spalten! Muss angegeben werden, wenn man im Select * benutzen will!")
						.withValueSeparator(' ').hasArg().create("c"));
	}

	/**
	 * In dieser Methode sind die if Unterscheidungen, um die angegebenen
	 * Arguemente auszuwerten.
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
		if (cmd.hasOption("s") || cmd.hasOption("sort")) {
			this.setToSort(cmd.getOptionValue("s"));
			// System.out.println(cmd.getOptionValue("s"));
		}
		if (cmd.hasOption("r") || cmd.hasOption("sortASC")) {
			this.setSort(cmd.getOptionValue("r"));
			// System.out.println(cmd.getOptionValue("r"));
		}
		if (cmd.hasOption("w") || cmd.hasOption("filtern")) {
			this.setWhere(cmd.getOptionValue("w"));
			// System.out.println(cmd.getOptionValue("w"));
		}
		if (cmd.hasOption("t") || cmd.hasOption("trenn")) {
			this.setTrenn(cmd.getOptionValue("t"));
			// System.out.println(cmd.getOptionValue("t"));
		}
		if (cmd.hasOption("f") || cmd.hasOption("trennf")) {
			this.setList(cmd.getOptionValue("f"));
			// System.out.println(cmd.getOptionValue("f"));
		}
		if (cmd.hasOption("o") || cmd.hasOption("output")) {
			this.setOutput(cmd.getOptionValue("o"));
			// System.out.println(cmd.getOptionValue("o"));
		}
		if (cmd.hasOption("T") || cmd.hasOption("table")) {
			this.setTable(cmd.getOptionValue("T"));
			// System.out.println(cmd.getOptionValue("T"));
		}
		if (cmd.hasOption("c") || cmd.hasOption("tableCount")) {
			this.setCount(cmd.getOptionValue("c"));
			// System.out.println(cmd.getOptionValue("T"));
		}
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getPass() {
		return pass;
	}

	public void setPass(String pass) {
		this.pass = pass;
	}

	public String getDb() {
		return db;
	}

	public void setDb(String db) {
		this.db = db;
	}

	public String getHost() {
		return host;
	}

	public void setHost(String host) {
		this.host = host;
	}

	public String getTable() {
		return table;
	}

	public void setTable(String table) {
		this.table = table;
	}

	public String getSort() {
		return sort;
	}

	public void setSort(String sort) {
		this.sort = sort;
	}

	public String getToSort() {
		return toSort;
	}

	public void setToSort(String toSort) {
		this.toSort = toSort;
	}

	public String getWhere() {
		return where;
	}

	public void setWhere(String where) {
		this.where = where;
	}

	public String getList() {
		return list;
	}

	public void setList(String list) {
		this.list = list;
	}

	public String getOutputData() {
		return outputData;
	}

	public void setOutputData(String outputData) {
		this.outputData = outputData;
	}

	public String getTrenn() {
		return trenn;
	}

	public void setTrenn(String trenn) {
		this.trenn = trenn;
	}

	public String getCount() {
		return count;
	}

	public void setCount(String count) {
		this.count = count;
	}

	public String getOutput() {
		return output;
	}

	public void setOutput(String output) {
		this.output = output;
	}
}
