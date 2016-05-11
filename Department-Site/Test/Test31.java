package se.term_project;

import junit.framework.TestCase;

public class Test31 extends TestCase {
	
	public void testUpdate_course() {
		SETermDAO dao = new SETermDAO();
		dao.DBConnect();
		boolean actual = dao.Update_course("1111111111111111", "11", "11", "11", "11", "11", "11", "11", "11", "11", "11");
		dao.close();
		assertEquals(actual,true);
	}

}
