package se.term_project;

import junit.framework.TestCase;

public class Test18 extends TestCase {

	public void testupdateAnnouncement() {
		SETermDAO dao = new SETermDAO();
		dao.DBConnect();
		boolean actual = dao.updateAnnouncement("1111111111111111111111111111", "11", "11");
		dao.close();
		assertEquals(actual,true);
	}
}
