package se.term_project;

import junit.framework.TestCase;

public class Test28 extends TestCase {
	
	public void testDeleteAnnouncement() {
		SETermDAO dao = new SETermDAO();
		dao.DBConnect();
		boolean actual = dao.DeleteAnnouncement("11111111111111111111111111111111");
		dao.close();
		assertEquals(actual,true);
	}

}
