package at.insy.exporter;

/**
 * @author FOCK
 *
 */
public class Main {
	/**
	 * Main
	 * @param args Konsoleneingabe
	 */
	public static void main(String[] args) {
//		for (int i = 0; i < args.length; i++) System.out.println(args[i]);
		Commands c = new Commands(args);
		Exporter ex = new Exporter(c);
		ex.check();
		ex.connect();
		ex.doQuery();
		ex.disconnect();
	}
}
