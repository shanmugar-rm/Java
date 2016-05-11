package se.term_project;

import junit.framework.TestCase;

public class Test12 extends TestCase {

	public void testinsertTest() {
		SETermDAO dao = new SETermDAO();
		dao.DBConnect();
		boolean actual = dao.insertTest("11111111111111111111111111111", "111111111111111", "111111111111111111111", "1111111111111111111111");
		dao.close();
		assertEquals(actual,false);
	}
}
