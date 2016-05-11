package se.bank.src;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
//import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class deleterecipient
 */
public class deleterecipient extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public deleterecipient() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		// response.getWriter().append("Served at:
		// ").append(request.getContextPath());
		String id = request.getParameter("x");
		dbconnection obj = new dbconnection();
		obj.modify("delete from recepient where recepient_id='" + id + "'");
		/*
		ResultSet rs = obj.selectdata("select * from recepient where recepient_id='" + id + "'");
		try {
			if (rs.next()) {
				String accno = rs.getString(2);
				String name = rs.getString(3);
				String lastname = rs.getString(4);
				DAO db_object = new DAO();
				db_object.DBConnect();
				String email = db_object.selectEmail(accno);
				System.out.println("email:" + email);
				System.out.println("accno:" + accno);
				System.out.println("name:" + name);
				System.out.println("lastname:" + lastname);			
				String subject = "Beneficiary has been deleted " + "Account number of the beneficiary: " + accno
						+ " Name : " + name + " " + lastname + " Sent from Worldwide Bank";
				SendMail sendemail = new SendMail();
				sendemail.email(subject, email);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		*/

		response.sendRedirect("viewrecipient.jsp");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		// doGet(request, response);

	}

}
