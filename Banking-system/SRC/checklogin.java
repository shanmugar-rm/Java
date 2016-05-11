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

//import demo.OTP;

/**
 * Servlet implementation class checklogin
 */
public class checklogin extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public checklogin() {
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

		PrintWriter out = response.getWriter();
		String action = request.getParameter("action");
		if (action == null) {
			request.setAttribute("Message", "");
			request.getRequestDispatcher("/index.jsp").forward(request, response);
		} else if (action.equals("forgotpassword")) {
			request.setAttribute("Message", " ");
			request.setAttribute("Message1", " ");
			request.getRequestDispatcher("/otppasswordreset.jsp").forward(request, response);

		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		// doGet(request, response);
		PrintWriter out = response.getWriter();
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String email;
		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/se_bank", "root", "root");
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery(
					"select * from act_desc where username='" + username + "' and password='" + password + "'");
			boolean status;
			status = rs.next();
			if (status) {
				System.out.println(username);
				if (username.equals("Admin")) {
					request.setAttribute("select_update", "select_date");
					RequestDispatcher dispatch = request.getRequestDispatcher("UpdateDateServlet");
					dispatch.forward(request, response);
				} else {
					String account_number = rs.getString(1);
					request.setAttribute("account_number", account_number);
					request.setAttribute("what_to", "get_act_details");
					out.println("login successfull");
					// RequestDispatcher
					// dispatch=request.getRequestDispatcher("AccountDetails");
					// dispatch.include(request,response);
					// request.setAttribute("Message", " ");
					// request.setAttribute("Message1", " ");
					OTP otp = new OTP();
					int num = otp.create();
					String subject = "One Time Password is " + num + " Sent from Worldwide Bank";
					try {
						String sql_query = "select emailid from registration where loginid = ?";
						PreparedStatement ps = con.prepareStatement(sql_query);
						ps.setString(1, username);
						ResultSet output = ps.executeQuery();
						if (output.next()) {
							email = output.getString(1);

							Database_otp database = new Database_otp();
							database.add(num, email, username, con);

							request.setAttribute("email", email);
							request.setAttribute("username", username);

							SendMail sendemail = new SendMail();

							sendemail.email(subject, email);
						}

						// return desc;
					} catch (Exception ex) {
						// return desc;
					}
					request.setAttribute("Message", " ");
					request.getRequestDispatcher("/otp.jsp").forward(request, response);
				}

			} else {
				// out.println("username or password doesnt match")
				request.setAttribute("zc", "username or password doesnt match");
				;
				RequestDispatcher dispatch = request.getRequestDispatcher("Login.jsp");
				dispatch.include(request, response);
			}
		} catch (Exception ex) {
			System.out.println(ex.toString());
		}
	}

}
