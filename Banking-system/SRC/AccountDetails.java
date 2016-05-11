package se.bank.src;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class AccountDetails
 */
public class AccountDetails extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AccountDetails() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		HttpSession s = request.getSession();
		String what_to = (String) request.getAttribute("what_to");
		if (what_to == null) {
			what_to = request.getParameter("what_to");
		}
		if (what_to == null) {
			what_to = s.getAttribute("what_to").toString();
		}
		if (what_to.equals("get_act_details")) {
			DAO db_object = new DAO();
			db_object.DBConnect();
			String acct_number = (String) request.getAttribute("account_number");
			if (acct_number == null ){
				acct_number = s.getAttribute("acct_number").toString();
			}
			ArrayList<Transaction> trans = new ArrayList<Transaction>();
			ArrayList<Notification> notifi = new ArrayList<Notification>();
			trans = db_object.GetTransactions(acct_number);
			notifi = db_object.GetNotifications(acct_number);
			String act_desc = db_object.selectDesc(acct_number);
			String email = db_object.selectEmail(acct_number);
			s.setAttribute("user_name", act_desc);
			s.setAttribute("acct_number", acct_number);
			s.setAttribute("email", email);
			db_object.DBClose();
			request.setAttribute("transactions", trans);
			request.setAttribute("notifications", notifi);
			request.getRequestDispatcher("AccountDetails.jsp").forward(request, response);
		}
		if (what_to.equals("update_notifi")) {
			int unique_id = (Integer.parseInt(request.getParameter("unique_id")));
			AutoPay get_notifi = new AutoPay();
			String auto = request.getParameter("auto");
			DAO db_object = new DAO();
			db_object.DBConnect();
			String acct_number = request.getParameter("account_number");
			ArrayList<Transaction> trans = new ArrayList<Transaction>();
			ArrayList<Notification> notifi = new ArrayList<Notification>();
			db_object.updateAuto(unique_id,auto);
			get_notifi = db_object.getNotification_unique(unique_id);
			if (auto.equals("Yes")) {
				db_object.insertAutopay(get_notifi);
			}
			if (auto.equals("No")) {
				db_object.deleteAutopay(get_notifi);
			}
			trans = db_object.GetTransactions(acct_number);
			notifi = db_object.GetNotifications(acct_number);
			String act_desc = db_object.selectDesc(acct_number);
			s.setAttribute("user_name", act_desc);
			s.setAttribute("acct_number", acct_number);
			db_object.DBClose();
			request.setAttribute("transactions", trans);
			request.setAttribute("notifications", notifi);
			request.getRequestDispatcher("AccountDetails.jsp").forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

	public boolean Overdraft (float balance, float amount) {
		return true;
	}
}
