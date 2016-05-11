package se.term_project;

import junit.framework.TestCase;

public class Test7 extends TestCase {
	
	public void testGetRequirements() {
		SETermDAO dao = new SETermDAO();
		dao.DBConnect();
		String actual = dao.GetRequirements("Computer Science", "MS");
		String expected = "Core - 13 credits, Electives - 16 credits, Intern/project - 3 credits";
		assertEquals(expected,actual);
	}

}
