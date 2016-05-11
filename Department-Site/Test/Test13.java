package se.term_project;

import java.util.ArrayList;

import junit.framework.TestCase;

public class Test13 extends TestCase {

	public void testgetTestList() {
		SETermDAO dao = new SETermDAO();
		dao.DBConnect();
		ArrayList<Create_test_M> actual = dao.getTestList();
		dao.close();
		assertEquals(actual.get(actual.size()-1).getUnique_id(),"1");
	}
}
