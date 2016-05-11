package se.term_project;

import junit.framework.TestCase;

public class Test27 extends TestCase {

	public void testDiscussionReplyInsert() {
		SETermDAO dao = new SETermDAO();
		dao.DBConnect();
		boolean actual = dao.DiscussionReplyInsert("1111111111111111111111111111", "11111111111111111111111", 1111111111);
		dao.close();
		assertEquals(actual,true);
	}
}
