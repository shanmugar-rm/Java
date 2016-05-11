package se.term_project;

import junit.framework.TestCase;

public class Test2 extends TestCase {
		
	public void testuser_present() {
		SETermDAO dao = new SETermDAO();
		dao.DBConnect();
		Login_sys user_details = new Login_sys();
		user_details.setUser_name("SR957737");
		user_details.setPassword_sys("India123$");
		user_details.setStudent_employee("Staff");
		boolean check_user_present = dao.user_present(user_details);
		dao.close();
		assertEquals(false,check_user_present);		
	}
}
