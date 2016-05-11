package se.term_project;

import junit.framework.TestCase;

public class Test17 extends TestCase {
	
	public void testdeleteDissHead() {
		SETermDAO dao = new SETermDAO();
		dao.DBConnect();
		boolean actual = dao.deleteDissHead("11111111111111111111111");
		dao.close();
		assertEquals(actual,true);
	}

}
