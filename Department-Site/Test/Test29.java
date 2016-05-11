package se.term_project;

import junit.framework.TestCase;

public class Test29 extends TestCase {
	
	public void testInsert_announcement() {
		SETermDAO dao = new SETermDAO();
		dao.DBConnect();
		boolean actual = dao.Insert_announcement("11111111111111", "111111111111111", "11", "11");
		dao.close();
		assertEquals(actual,true);
	}

}
