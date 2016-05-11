package se.bank.src;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class otppasswordreset
 */
public class otppasswordreset extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public otppasswordreset() {
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
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
		String action = request.getParameter("action");
		if (action.equals("otpgenerate")) {
			OTP otp = new OTP();

			int num = otp.create();
			String email = request.getParameter("email");
			String username = request.getParameter("username");
			request.setAttribute("email", email);
			request.setAttribute("username", username);
			String subject = "One TIme Password is " + num + " Sent from Worldwide Bank";
			// String email = "ashishjadhav53@gmail.com";
			PrintWriter out = response.getWriter();
			// out.println("Test is" + test);

			try {
				Class.forName("com.mysql.jdbc.Driver");
			} catch (ClassNotFoundException e) {
				out.println("Can't load database driver");
				// e.printStackTrace();
				// String strClassPath = System.getProperty("java.class.path");

				// System.out.println("Classpath is " + strClassPath);
				return;
			}

			Connection conn = null;

			try {
				conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/se_bank", "root", "root");
			} catch (SQLException e) {
				out.println("Can't connect to database.");
				return;
			}
			String desc = null;
			try {
				String sql_query = "select emailid from registration where loginid = ?";
				PreparedStatement ps = conn.prepareStatement(sql_query);
				ps.setString(1, username);
				ResultSet output = ps.executeQuery();
				if (output.next()) {
					desc = output.getString(1);
				}
				// return desc;
			} catch (Exception ex) {
				// return desc;
			}
			if (desc.equals(email)) {
				
			

			Database_otp database = new Database_otp();

			try {
				database.add(num, email, username, conn);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			SendMail sendemail = new SendMail();

			sendemail.email(subject, email);
			request.setAttribute("Message1", " ");
			request.setAttribute("Message", " ");
			request.getRequestDispatcher("/otppasswordreset.jsp").forward(request, response);
			}
			else{
				request.setAttribute("Message1", "Emaid or loginid does not match ");
				request.setAttribute("Message", " ");
				request.getRequestDispatcher("/otppasswordreset.jsp").forward(request, response);
			}

		} else if (action.equals("resetpassword")) {

			String email = request.getParameter("email");
			String username = request.getParameter("username");
			request.setAttribute("email", email);
			request.setAttribute("username", username);

			PrintWriter out = response.getWriter();
			// out.println("0000");
			int otp = Integer.parseInt(request.getParameter("otp"));
			// int otp = 111111;
			// out.println("AAAAA");
			// String email=request.getParameter("email");
			// String email = "ashishjadhav53@gmail.com";
			// out.println("BBBB");

			try {
				Class.forName("com.mysql.jdbc.Driver");
			} catch (ClassNotFoundException e) {
				out.println("Can't load database driver");
				// e.printStackTrace();
				// String strClassPath = System.getProperty("java.class.path");

				// System.out.println("Classpath is " + strClassPath);
				return;
			}

			Connection conn = null;

			try {
				conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/se_bank", "root", "root");
			} catch (SQLException e) {
				out.println("Can't connect to database.");
				return;
			}

			Database_otp database = new Database_otp();

			try {
				if (database.search(otp, email, username, conn)) {

					database.remove(otp, email, username, conn);
					request.setAttribute("Message", " ");
					request.getRequestDispatcher("/resetpassword.jsp").forward(request, response);
				}

				else {
					request.setAttribute("Message", "Please enter correct otp");
					request.setAttribute("Message1", " ");
					request.setAttribute("email", email);
					request.setAttribute("username", username);
					request.getRequestDispatcher("/otppasswordreset.jsp").forward(request, response);
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
	}
}
