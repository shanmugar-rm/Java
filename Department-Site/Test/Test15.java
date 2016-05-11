package se.term_project;

import java.util.ArrayList;

import junit.framework.TestCase;

public class Test15 extends TestCase {
	
	public void testselectAlumni() {
		SETermDAO dao = new SETermDAO();
		dao.DBConnect();
		ArrayList<Alumni_info> actual = dao.selectAlumni();
		dao.close();
		assertEquals(actual.get(0).getUnique_id(),"1");
	}

}
