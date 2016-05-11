package se.term_project;

import junit.framework.TestCase;

public class Test9 extends TestCase {

	public void testinsertStudent() {
		SETermDAO dao = new SETermDAO();
		dao.DBConnect();
		boolean actual = dao.insertStudent("10", "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa", "bbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbb");
		dao.close();
		assertEquals(actual,false);
	}
}
