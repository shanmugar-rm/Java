package se.bank.src;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class EditUserDetails
 */
public class EditUserDetails extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EditUserDetails() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		doPost(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//doGet(request, response);
		HttpSession session = request.getSession();
		String check_parameter = request.getParameter("what_to");
		String act_number = (String) request.getParameter("x");		
		if (check_parameter == null) {
			DAO dao = new DAO();
			dao.DBConnect();
			RegistrationModel user_details = dao.getDetails(act_number);
			session.setAttribute("user_detail", user_details);
			dao.DBClose();
			request.getRequestDispatcher("Editdetails.jsp").forward(request, response);
		}
		else {
			DAO dao = new DAO();
			dao.DBConnect();
			String first_name = request.getParameter("firstname");
			String lastname = request.getParameter("lastname");
			String email_id = request.getParameter("email_id");
			String phone_number = request.getParameter("phone_number");
			String address = request.getParameter("address");
			String password = request.getParameter("password");
			System.out.println(password);		
			dao.updateRegistration(first_name, lastname, email_id, phone_number, address, act_number);
			if (password!=null && !password.isEmpty() && password.length() > 1) {
				dao.updatePassword(password, act_number);
			}
			RegistrationModel user_details = dao.getDetails(act_number);
			session.setAttribute("user_detail", user_details);
			request.setAttribute("message", "Information Updated");			
			dao.DBClose();
			request.getRequestDispatcher("Editdetails.jsp").forward(request, response);
		}
	}

}
