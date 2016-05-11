package se.term_project;

import java.util.ArrayList;

import junit.framework.TestCase;

public class Test11 extends TestCase {

	public void testgetStudentList() {
		SETermDAO dao = new SETermDAO();
		dao.DBConnect();
		ArrayList<Student_test> actual = dao.getStudentList();
		dao.close();
		assertEquals(actual.get(0).getparentid(),"1");
	}
}
