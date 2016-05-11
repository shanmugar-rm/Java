package se.term_project;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class LoginServlet
 */
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//doGet(request, response);
		String user_name = request.getParameter("user_name");
		String password = request.getParameter("password");
		String student_employee = request.getParameter("type");
		Login_sys user_details = new Login_sys();
		Login_sys user_details_db = new Login_sys();
		user_details.setUser_name(user_name);
		user_details.setPassword_sys(password);
		user_details.setStudent_employee(student_employee);
		SETermDAO DAO = new SETermDAO();
		DAO.DBConnect();
		boolean present_db = DAO.user_present(user_details);
		if (present_db) {
			user_details_db = DAO.GetUserdetails(user_details);
			DAO.close();
			HttpSession session = request.getSession();
			session.setAttribute("user_details_db", user_details_db);
			if (user_details.getStudent_employee().equals("Student")) {
				request.getRequestDispatcher("/StudentMainServlet?x=Home").forward(request, response);
			}
			if (user_details.getStudent_employee().equals("Faculty")) {
				request.getRequestDispatcher("/FacultyMainServlet?x=Home").forward(request, response);
			}
			if (user_details.getStudent_employee().equals("Staff")) {
				request.getRequestDispatcher("/StaffMainServlet?x=Home").forward(request, response);
			}
		}
		else {
			DAO.close();
			String message = "Username / Password is not valid";
			request.setAttribute("zc", message);
			request.setAttribute("n_student_employee", student_employee);
			request.getRequestDispatcher("Welcome.jsp").forward(request, response);
		}
	}

}
