package se.term_project;

import junit.framework.TestCase;

public class Test19 extends TestCase {
	
	public void testupdatePassword() {
		SETermDAO dao = new SETermDAO();
		dao.DBConnect();
		boolean actual = dao.updatePassword("aaaaaaaaaaaaaaaa", "1111111111111111111111111");
		dao.close();
		assertEquals(actual,true);
	}

}
