package se.term_project;

import junit.framework.TestCase;

public class Test21 extends TestCase {

	public void testAddResource() {
		SETermDAO dao = new SETermDAO();
		dao.DBConnect();
		boolean actual = dao.AddResource("11111111111111111111111111111111111111111111111111111111", "11");
		dao.close();
		assertEquals(actual,false);
	}
}
