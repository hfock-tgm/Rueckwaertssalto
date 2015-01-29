package at.salto.test;

import org.junit.Before;
import org.junit.Test;

import at.salto.parsen.Commands;
import static org.junit.Assert.*;

/**
 * Das ist die Testerklasse von Commands
 * 
 * @author Hagen Fock 4AHIT
 * @author Michael Weinberger 4AHIT
 * @version 2015-01-28
 * @email hfock@student.tgm.ac.at
 * @email mweinberger@student.tgm.ac.at
 *
 */
public class TestCommands {
	private Commands c;

	/**
	 * 
	 */
	@Before
	public void initTest() {
		String[] args = { "-h", "localhost", "-u", "root", "-p", "1234", "-d",
				"premiere"};
		this.c = new Commands(args);
		c.init();
	}

	/**
	 * 
	 */
	@Test
	public void testParse() {
		c.pars();
		assertEquals("root", c.getUser());
		assertEquals("1234", c.getPass());
		assertEquals("localhost", c.getHost());
		assertEquals("premiere", c.getDb());
	}
}
