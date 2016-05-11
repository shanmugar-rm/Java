package se.term_project;

import junit.framework.TestCase;

public class Test24 extends TestCase {

	public void testinsertPhD() {
		SETermDAO dao = new SETermDAO();
		dao.DBConnect();
		boolean actual = dao.insertPhD("1111111111111111111111", "1111111111111111", "11", "11");
		dao.close();
		assertEquals(actual,false);
	}
}
