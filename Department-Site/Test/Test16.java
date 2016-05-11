package se.term_project;

import junit.framework.TestCase;

public class Test16 extends TestCase {

	public void testdeleteDissContent() {
		SETermDAO dao = new SETermDAO();
		dao.DBConnect();
		boolean actual = dao.deleteDissContent("11111111111111111111111", "1111111111111111111111");
		dao.close();
		assertEquals(actual,true);
	}
}
