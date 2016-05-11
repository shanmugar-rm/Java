package se.term_project;

import java.util.ArrayList;

import junit.framework.TestCase;

public class Test23 extends TestCase {

	public void testgetPhdStudentList() {
		SETermDAO dao = new SETermDAO();
		dao.DBConnect();
		ArrayList<Phd_status> actual = dao.getPhdStudentList();
		dao.close();
		assertEquals(actual.get(0).getUnique_id(),"1");
	}
}
