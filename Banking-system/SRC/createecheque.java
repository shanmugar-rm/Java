package se.bank.src;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Random;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class createecheque
 */
public class createecheque extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public createecheque() {
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
		} else if (action.equals("echeque")) {
			request.setAttribute("Message", " ");
			Random randomGenerator = new Random();
			int randomInt = randomGenerator.nextInt(8999999) + 1000000;
			String str = Integer.toString(randomInt);
			request.setAttribute("echeque_no", str);
			request.getRequestDispatcher("/createecheque.jsp").forward(request, response);

		}

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
		PrintWriter out = response.getWriter();
		String action = request.getParameter("action");
		if (action == null) {
			request.setAttribute("Message", "");
			request.getRequestDispatcher("/index.jsp").forward(request, response);
		} else if (action.equals("createecheque")) {

			HttpSession session = request.getSession();
			String user_accno = (String) session.getAttribute("acct_number");
			String from_username = (String) session.getAttribute("act_desc");
			String from_email = (String) session.getAttribute("email");
			String amount = request.getParameter("amount");
			String firstname = request.getParameter("firstname");
			String lastname = request.getParameter("lastname");
			String echeque_no = request.getParameter("echeque_no");
			String recepient_accno = request.getParameter("recepient_accno");
			System.out.println("amount = "+ amount);
			System.out.println("firstname = "+ firstname);
			System.out.println("lastname = "+ lastname);
			System.out.println("echeque_no = "+ echeque_no);
			System.out.println("recepient_accno = "+ recepient_accno);
			try {
				Class.forName("com.mysql.jdbc.Driver").newInstance();

				Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/se_bank", "root", "root");
				Statement st = con.createStatement();
				ResultSet rs = st
						.executeQuery("select * from registration where accountnumber='" + recepient_accno + "' and name='" + firstname + "' and lastname='" + lastname + "'");
				boolean status;
				status = rs.next();
				if (status) {
					// request.setAttribute("message", "Registration
					// Successful");
					String name = rs.getString(1);
					String lstname = rs.getString(10);
					//if (name.equals(firstname) && lstname.equals(lastname)) {
						DAO db_object = new DAO();
						db_object.DBConnect();
						String email_to = db_object.selectEmail(recepient_accno);
						String subject1 ="echeque (echeque number: "+ echeque_no+")" +" from your account to the account: "
								+ recepient_accno + " Sent from Worldwide Bank";
						String subject2 = "echeque (echeque number: "+ echeque_no+")" +" to your account from the account: "
								+ user_accno + " Sent from Worldwide Bank";
						SendMail sendemail = new SendMail();
						sendemail.email(subject1, from_email);
						SendMail sendemail1 = new SendMail();
						sendemail1.email(subject2, email_to);
						Random rand = new Random();
						int  n = rand.nextInt(999999999) + 1;
						st.execute("insert into echeque values('" + user_accno + "','" + recepient_accno + "','"
								+ firstname + "','" + lastname + "','" + echeque_no + "','" + amount + "','" + n + "')");
					//}

					// st.execute("insert into account(Accountnumber,Amount)
					// values ('"+n+"','500')");
						request.setAttribute("amount", amount);
						request.setAttribute("firstname", firstname);
						request.setAttribute("lastname", lastname);//recepient_accno
						request.setAttribute("echeque_no", echeque_no);
						request.setAttribute("recepient_accno", recepient_accno);
						request.getRequestDispatcher("/echequesummary.jsp").forward(request, response);
					
				}else{
					//Message
					Random randomGenerator = new Random();
					int randomInt = randomGenerator.nextInt(8999999) + 1000000;
					String str = Integer.toString(randomInt);
					request.setAttribute("echeque_no", str);
					request.setAttribute("Message", "Incorrect account details");
					request.getRequestDispatcher("/createecheque.jsp").forward(request, response);
					
				}
				

			} catch (Exception ex) {

				System.out.println(ex.toString());
			}
		}
	}
}
