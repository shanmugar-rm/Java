package se.term_project;

import junit.framework.TestCase;

public class Test10 extends TestCase {
	
	public void testsetMark() {
		SETermDAO dao = new SETermDAO();
		dao.DBConnect();
		boolean actual = dao.setMark("10000000000000000000000000000000000", "10000000000000000000000000000000000");
		dao.close();
		assertEquals(actual,true);
	}

}
