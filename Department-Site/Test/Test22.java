package se.term_project;

import junit.framework.TestCase;

public class Test22 extends TestCase {
	
	public void testupdatePhD() {
		SETermDAO dao = new SETermDAO();
		dao.DBConnect();
		boolean actual = dao.updatePhD("1111111111111111111", "111111111111111");
		dao.close();
		assertEquals(actual,true);
	}
}
