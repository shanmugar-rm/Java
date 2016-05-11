package se.term_project;

import junit.framework.TestCase;

public class Test30 extends TestCase {

	public void testDeleteCourse() {
		SETermDAO dao = new SETermDAO();
		dao.DBConnect();
		boolean actual = dao.DeleteCourse("1111111111111111111111");
		dao.close();
		assertEquals(actual,true);
	}
}
