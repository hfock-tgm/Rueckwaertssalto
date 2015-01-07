package at.salto;

public class Main {
	public static void main(String[] args) {
		Commands c = new Commands(args);
		ConnectDB db = new ConnectDB(c);
	}
}
