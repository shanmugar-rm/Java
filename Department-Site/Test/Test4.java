package se.term_project;

import java.util.ArrayList;

import junit.framework.TestCase;

public class Test4 extends TestCase {
	
	public void testGetResources() {
		SETermDAO dao = new SETermDAO();
		dao.DBConnect();		
		ArrayList<ResourseStatus> List = dao.GetResources();
		ArrayList<ResourseStatus> actual = new ArrayList<ResourseStatus>();
		ResourseStatus test = new ResourseStatus();
		test.setResourse_name("A");
		actual.add(test);	
		dao.close();
		assertEquals(actual.get(0).getResourse_name(),List.get(0).getResourse_name());	
	}
}
