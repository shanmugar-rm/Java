package se.term_project;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class RegistrationServlet
 */
public class RegistrationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RegistrationServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	/* (non-Javadoc)
	 * @see javax.servlet.http.HttpServlet#doPost(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//doGet(request, response);
		//HttpSession session = request.getSession();
		Login_sys user_info = new Login_sys();
		user_info.setFirst_name(request.getParameter("First_name"));
		user_info.setLast_name(request.getParameter("Last_name"));
		user_info.setemail_id(request.getParameter("Email_id"));
		user_info.setAdvisor_name(request.getParameter("Advisor_name"));
		user_info.setProgram_taken(request.getParameter("Program_taken"));
		String phd_check = "";
		if (request.getParameter("Program_taken").equals("BS") || request.getParameter("Program_taken").equals("MS") || request.getParameter("Program_taken").equals("PhD")) {
			user_info.setStudent_employee("Student");
			if (request.getParameter("Program_taken").equals("PhD")) {
				phd_check = "PhD";
			}
		}
		else {
			user_info.setStudent_employee(request.getParameter("Program_taken"));
		}		
		user_info.setUser_name(request.getParameter("user_name"));
		user_info.setPassword_sys(request.getParameter("password"));		
		String year_joined = request.getParameter("year_joined");		
		String major = request.getParameter("major");
		user_info.setyear_joined(year_joined);
		user_info.setmajor(major);
		SETermDAO db_obj = new SETermDAO();
		db_obj.DBConnect();
		boolean check_already = db_obj.Login_sys_check(user_info);
		if(check_already) {
			boolean check = db_obj.Login_sys_insert(user_info);
			if (phd_check.equals("PhD")) {
				db_obj.insertPhD(request.getParameter("user_name"), year_joined,request.getParameter("First_name"),request.getParameter("Last_name"));
			}			
			db_obj.close();
			if(check) {
				request.setAttribute("message", "Registration successfull");
				request.getRequestDispatcher("Welcome.jsp").forward(request, response);
			}
			else {
				//session.setAttribute("new_user_detail", user_info);
				request.setAttribute("message", "Something went wrong, Please try again");
				request.getRequestDispatcher("Registration.jsp").forward(request, response);
			}
		}		
		else {
			//session.setAttribute("new_user_detail", user_info);
			request.setAttribute("message", "User already registered");
			request.getRequestDispatcher("Registration.jsp").forward(request, response);
		}
	}

}
