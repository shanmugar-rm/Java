package se.term_project;

import junit.framework.TestCase;

public class Test14 extends TestCase {

	public void testupdateAlumniInfo() {
		SETermDAO dao = new SETermDAO();
		dao.DBConnect();
		boolean actual = dao.updateAlumniInfo("1000000000000000000000", "111111111111111111", "11111111111111111", "11111111");
		dao.close();
		assertEquals(actual,true);
	}
}
