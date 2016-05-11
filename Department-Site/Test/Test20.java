package se.term_project;

import junit.framework.TestCase;

public class Test20 extends TestCase {

	public void testUpdateUser() {
		SETermDAO dao = new SETermDAO();
		dao.DBConnect();
		boolean actual = dao.UpdateUser("11111111111111111111111", "1111111111111111111", "11111111111111", "1111111111111111111");
		dao.close();
		assertEquals(actual,true);
	}
}
