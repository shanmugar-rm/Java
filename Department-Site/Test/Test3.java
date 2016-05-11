package se.term_project;

import junit.framework.TestCase;

public class Test3 extends TestCase {
	
	public void testGetUserdetails() {
		SETermDAO dao = new SETermDAO();
		dao.DBConnect();
		Login_sys user_details = new Login_sys();
		user_details.setUser_name("SR957737");		
		user_details.setStudent_employee("Student");
		Login_sys check_user_details = dao.GetUserdetails(user_details);
		Login_sys actual = new Login_sys();	
		actual.setPassword_sys("India123$");	
		dao.close();
		assertEquals(actual.getpassword_sys(),check_user_details.getpassword_sys());	
	}

}
