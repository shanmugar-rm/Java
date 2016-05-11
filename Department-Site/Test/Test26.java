package se.term_project;

import java.util.ArrayList;

import junit.framework.TestCase;

public class Test26 extends TestCase {
	
	public void testGetChildpost() {
		SETermDAO dao = new SETermDAO();
		dao.DBConnect();
		ArrayList<Discussion> actual = dao.GetChildpost();
		dao.close();
		assertEquals(actual.get(0).getId(),2);
	}

}
