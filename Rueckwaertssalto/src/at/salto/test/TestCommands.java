package at.salto.test;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;

import at.salto.parsen.Commands;
import static org.junit.Assert.*;

/**
 * Das ist die Testerklasse von Commands
 * 
 * @author FOCK
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
