package se.term_project;

import junit.framework.TestCase;

public class Test25 extends TestCase {
	
	public void testLogin_sys_check() {
		SETermDAO dao = new SETermDAO();
		dao.DBConnect();
		Login_sys user_info = new Login_sys();
		user_info.setUser_name("11111111");
		boolean actual = dao.Login_sys_check(user_info);
		dao.close();
		assertEquals(actual,true);
	}

}
