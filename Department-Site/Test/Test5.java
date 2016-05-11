package se.term_project;

import junit.framework.TestCase;

public class Test5 extends TestCase {
	public void testGetResourcesforadate() {
		SETermDAO dao = new SETermDAO();
		dao.DBConnect();		
		ResourseStatus List = dao.GetResourcesforadate("A", "2016-05-02");
		ResourseStatus actual = new ResourseStatus();		
		actual.setResourse_name("A");		
		dao.close();
		assertEquals(actual.getResourse_name(),List.getResourse_name());	
	}
}
