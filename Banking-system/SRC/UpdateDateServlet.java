package se.bank.src;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class UpdateDateServlet
 */
public class UpdateDateServlet extends HttpServlet {
	String display_msg;
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateDateServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());	
		DAO db_object = new DAO();
		db_object.DBConnect();
		String select_update = request.getParameter("select_update");
		if (select_update == null) {
			select_update = (String) request.getAttribute("select_update");
		}
		if (select_update.equals("select_date")) {
			system_date bank_date = new system_date();
			bank_date = db_object.GetSystem_date();
			db_object.DBClose();
			request.setAttribute("db_date", bank_date);
			request.getRequestDispatcher("Update_date.jsp").forward(request, response);
		}
		if (select_update.equals("update_date")) {
			String update_date = request.getParameter("database_date");
			db_object.UpdateSystem_date(update_date);
			ArrayList<AutoPay> auto_pays = new ArrayList<AutoPay>();
			auto_pays = db_object.getAllAutoPay_todate(update_date);
			for (int i =0;i< auto_pays.size();i++) {
				db_object.insertTransactionAutopay(auto_pays.get(i));
				boolean test = db_object.insertTransactionAutopay_cr(auto_pays.get(i));
				System.out.println(test);
				db_object.updateNotificationAutopay(auto_pays.get(i));
				db_object.updateAutopay(auto_pays.get(i));
			}
			request.setAttribute("db_date", update_date);
			display_msg = "fine";
			request.setAttribute("display_msg", display_msg);
			system_date update_date1 = new system_date();
			update_date1.setSystem_date(update_date);
			db_object.DBClose();
			request.setAttribute("db_date", update_date1);
			request.getRequestDispatcher("Update_date.jsp").forward(request, response);
		}		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
