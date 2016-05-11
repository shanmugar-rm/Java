package se.term_project;

import java.util.ArrayList;

import junit.framework.TestCase;

public class Test8 extends TestCase {
	
	public void testGetSuggestions() {
		SETermDAO dao = new SETermDAO();
		dao.DBConnect();
		ArrayList<Suggestion> actual = dao.GetSuggestions("Computer Science");
		Suggestion expected = new Suggestion();
		expected.setconcentration("Computer Science");
		dao.close();
		assertEquals(actual.get(0).getconcentration(),expected.getconcentration());
	}

}
