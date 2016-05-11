package se.term_project;

import junit.framework.TestCase;

public class Test6 extends TestCase {

	public void testgetStudentStatus() {
		SETermDAO dao = new SETermDAO();
		dao.DBConnect();	
		Student_status actual = dao.getStudentStatus("SR957737");		
		dao.close();
		Student_status expected = new Student_status();
		expected.setfname("Shanmugar");
		assertEquals(actual.getfname(),expected.getfname());
	}
}
