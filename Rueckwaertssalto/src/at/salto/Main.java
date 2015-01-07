package at.salto;

/**
 * Main
 * 
 * @author FOCK
 *
 */
public class Main {
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Commands c = new Commands(args);
		ConnectDB db = new ConnectDB(c);
		db.connect();
		db.disconnect();
	}
}
