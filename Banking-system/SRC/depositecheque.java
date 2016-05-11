package se.bank.src;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Random;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class depositecheque
 */
public class depositecheque extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public depositecheque() {
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
		System.out.println("In get");
		request.setAttribute("Message", "");
		request.getRequestDispatcher("/depositecheque.jsp").forward(request, response);
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
		String action = request.getParameter("action");
		System.out.println("In Post");
		if (action == null) {
			request.setAttribute("Message", "");
			request.getRequestDispatcher("/index.jsp").forward(request, response);
		} else if (action.equals("echeque")) {

			HttpSession session = request.getSession();
			String recepient_accno = (String) session.getAttribute("acct_number");
			// String to_username = (String) session.getAttribute("user_name");
			String to_email = (String) session.getAttribute("email");

			// String firstname = request.getParameter("firstname");
			// String lastname = request.getParameter("lastname");
			String echeque_no = request.getParameter("echeque_no");
			DAO db_object = new DAO();
			db_object.DBConnect();
			String date = db_object.GetSystem_date().getSystem_date();

			// System.out.println("amount = "+ amount);
			// System.out.println("firstname = "+ amount);
			// System.out.println("to_username = "+ to_username);
			System.out.println("to_email = " + to_email);
			System.out.println("recepient_accno = " + recepient_accno);

			try {
				Class.forName("com.mysql.jdbc.Driver").newInstance();

				Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/se_bank", "root", "root");
				Statement st = con.createStatement();
				ResultSet rs = st.executeQuery("select * from echeque where recepient_accno='" + recepient_accno + "'");
				boolean status;
				status = rs.next();
				if (status) {

					
					System.out.println("status =" + status +"Rs= "+ rs + "Account match= " + rs.getString(2).equals(recepient_accno) + "echeque match "
							+ rs.getString(5).equals(echeque_no));
					if (status && rs.getString(2).equals(recepient_accno) && rs.getString(5).equals(echeque_no)) {
						String from_accno = rs.getString(1);
						String amount = rs.getString(6);

						float strAmount = Float.parseFloat(amount);
						dbconnection obj = new dbconnection();
						ResultSet rs3 = obj
								.selectdata("select balance from transaction where account_number='" + from_accno + "'"
										+ "and transaction_id = (select max(transaction_id) from transaction where account_number ='"
										+ from_accno + "')");
						System.out.println("Accountnumber2");
						ResultSet rs1 = obj.selectdata(
								"select balance from transaction where account_number='" + recepient_accno + "'"
										+ "and transaction_id = (select max(transaction_id) from transaction where account_number ='"
										+ recepient_accno + "')");
						System.out.println("Registration1");
						ResultSet rs2 = obj
								.selectdata("select * from registration where accountnumber='" + recepient_accno + "'");
						System.out.println("Registration2");
						if (rs2.next()) {
							while (rs3.next()) {
								// String name = srs.getString("COF_NAME");
								float amount1 = rs3.getFloat(1);
								System.out.println("Account Number:" + from_accno);
								System.out.println("Amount1:" + amount1);
								float facc = amount1 - strAmount;

								System.out.println("Facc:" + facc);
								while (rs1.next()) {
									// String name = srs.getString("COF_NAME");
									float amount2 = rs1.getFloat(1);
									// System.out.println("Account Number:" +
									// to_accno);
									System.out.println("Amount2:" + amount2);
									float toacc = amount2 + strAmount;
									System.out.println("toacc:" + toacc);
									int trans_id1 = db_object.selectMaxTranID(from_accno) + 1;
									int trans_id2 = db_object.selectMaxTranID(recepient_accno) + 1;
									String db = "db";
									String cr = "cr";
									String desc1 = db_object.selectDesc(recepient_accno) + " " + recepient_accno;
									String desc2 = db_object.selectDesc(from_accno) + " " + from_accno;
									String email_from = db_object.selectEmail(from_accno);
									obj.modify("insert into transaction values('" + from_accno + "','" + trans_id1
											+ "','" + date + "','" + from_accno + "','" + recepient_accno + "','"
											+ strAmount + "','" + db + "','" + facc + "','" + desc1 + "')");
									obj.modify("insert into transaction values('" + recepient_accno + "','" + trans_id2
											+ "','" + date + "','" + from_accno + "','" + recepient_accno + "','"
											+ strAmount + "','" + cr + "','" + toacc + "','" + desc2 + "')");
									String subject1 = strAmount
											+ "$ has been transeferred from your account to the account: "
											+ recepient_accno + " Sent from Worldwide Bank";
									String subject2 = strAmount
											+ "$ has been transeferred to your account from the account: " + from_accno
											+ " Sent from Worldwide Bank";
									SendMail sendemail = new SendMail();
									sendemail.email(subject1, email_from);
									SendMail sendemail1 = new SendMail();
									sendemail1.email(subject2, to_email);

									// obj.modify("update account set Amount='"+
									// facc +"' where Accountnumber='"+
									// from_accno
									// +"'");
									// obj.modify("update account set Amount='"+
									// toacc +"' where Accountnumber='"+
									// to_accno
									// +"'");
									System.out.println("Fundtransfer");
									request.setAttribute("Message", "Echeque successfuly deposited");
									String sql = "delete from `echeque` where echeque_no=?;";
									// UPDATE Customers
									// SET ContactName='Alfred Schmidt',
									// City='Hamburg'
									// WHERE CustomerName='Alfreds Futterkiste';
									PreparedStatement stmt = con.prepareStatement(sql);

									stmt.setString(1, echeque_no);

									// stmt.setInt(2, num);

									Integer rs5 = stmt.executeUpdate();

									stmt.close();
									// ResultSet rs3=obj.selectdata("select
									// transfer_id from fundtransfer where
									// transfer_date='"+date+"'");
									// while(rs3.next())
									// {
									// String str =
									// rs3.getString("transfer_id");
									// int transferid = Integer.parseInt(str);
									// System.out.println("transferid:"+transferid);
									// request.getRequestDispatcher("depositcheque.jsp").forward(request,
									// response);
									// response.sendRedirect("transfersummary.jsp?x="+transferid);
									// }
								}

							}
						}
						// st.execute("insert into
						// registration(name,type,emailid,loginid,password,phone,address,accountnumber,lastname)
						// values
						// ('"+Firstname+"','"+acctype+"','"+email+"','"+username+"','"+password+"','"+phone+"','"+address+"','"+n+"','"+Lastname+"')");
						// st.execute("insert into account(Accountnumber,Amount)
						// values ('"+n+"','500')");

						// request.getRequestDispatcher("/depositecheque.jsp").forward(request,
						// response);
					} else {
						System.out.println("Wrong echeque numbers");
						request.setAttribute("Message", "Wrong echeque numbers");
						// request.getRequestDispatcher("/depositecheque.jsp").forward(request,
						// response);
					}
				}else{
					request.setAttribute("Message", "No echeque exits");
				}
			} catch (Exception ex) {
				System.out.println(ex.toString());
			}
			request.getRequestDispatcher("/depositecheque.jsp").forward(request, response);
		}
	}
}
