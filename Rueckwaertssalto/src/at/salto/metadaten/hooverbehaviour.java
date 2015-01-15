package at.salto.metadaten;

import java.sql.Connection;
import java.util.ArrayList;

/**
 * @author FOCK
 *
 */
public interface hooverbehaviour {
	/**
	 * @param con 
	 * @param table 
	 * @return 
	 * 
	 */
	public ArrayList<String> hooverMetadata(Connection con, String table);
}
