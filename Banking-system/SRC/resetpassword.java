package se.bank.src;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class resetpassword
 */
public class resetpassword extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public resetpassword() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
		String action = request.getParameter("action");
		if (action.equals("resetpassword")) {
			OTP otp = new OTP();
			String password1 = request.getParameter("password1");
			String password2 = request.getParameter("password2");
			String email = request.getParameter("email");
			String username = request.getParameter("username");
			PrintWriter out = response.getWriter();
			if (password1.equals(password2)) {

				try {
					Class.forName("com.mysql.jdbc.Driver");
				} catch (ClassNotFoundException e) {
					out.println("Can't load database driver");
					// e.printStackTrace();
					// String strClassPath =
					// System.getProperty("java.class.path");

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
				String sql = "UPDATE act_desc SET password = ? where username = ?;";
				PreparedStatement stmt;
				try {
					stmt = conn.prepareStatement(sql);
					stmt.setString(1, password1);
					stmt.setString(2, username);

					Integer rs = stmt.executeUpdate();

					stmt.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				String sql1 = "UPDATE registration SET password = ? where loginid = ?;";
				PreparedStatement stmt1;
				try {
					stmt1 = conn.prepareStatement(sql1);
					stmt1.setString(1, password1);
					stmt1.setString(2, username);

					Integer rs = stmt1.executeUpdate();

					stmt1.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				String subject = "Your Password has been changed. Sent from Worldwide Bank";
				SendMail sendemail = new SendMail();
				sendemail.email(subject, email);
				request.getRequestDispatcher("/Login.jsp").forward(request,response);

			}
			else{
				request.setAttribute("Message", "Password does not match");
				request.getRequestDispatcher("/resetpassword.jsp").forward(request,response);
			}
			/*
			 * int num = otp.create(); String password1
			 * =request.getParameter("password1"); //String email =
			 * "ashishjadhav53@gmail.com"; PrintWriter out =
			 * response.getWriter();
			 * 
			 * try { Class.forName("com.mysql.jdbc.Driver"); } catch
			 * (ClassNotFoundException e) { out.println(
			 * "Can't load database driver"); // e.printStackTrace(); // String
			 * strClassPath = System.getProperty("java.class.path");
			 * 
			 * // System.out.println("Classpath is " + strClassPath); return; }
			 * 
			 * Connection conn = null;
			 * 
			 * try { conn = DriverManager.getConnection(
			 * "jdbc:mysql://localhost:3306/se_bank", "root", "root"); } catch
			 * (SQLException e) { out.println("Can't connect to database.");
			 * return; }
			 * 
			 * Database_otp database = new Database_otp();
			 * 
			 * try { database.add(num,email, conn); } catch (SQLException e) {
			 * // TODO Auto-generated catch block e.printStackTrace(); }
			 * 
			 * SendMail sendemail = new SendMail();
			 * 
			 * //sendemail.email(num,email);
			 * request.getRequestDispatcher("/Login.jsp").forward(request,
			 * response);
			 */

		}
	
	}

}
