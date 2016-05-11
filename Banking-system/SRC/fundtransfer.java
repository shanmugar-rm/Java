package se.bank.src;

import java.io.IOException;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class fundtransfer
 */
@SuppressWarnings("unused")
public class fundtransfer extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public fundtransfer() {
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
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		// doGet(request, response);
		HttpSession s = request.getSession();
		String recipientid = request.getParameter("id");
		String from_accno = s.getAttribute("acct_number").toString();
		String s_to_accno = request.getParameter("accno");
		int to_accno = Integer.parseInt(request.getParameter("accno"));
		String name = request.getParameter("username");
		String email_from = request.getParameter("email");
		String amount = request.getParameter("amount");
		String desc = request.getParameter("desc");
		s.setAttribute("desc_transfer", desc);
		// DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd hh:mm:ss");
		// Calendar cal = Calendar.getInstance();
		DAO db_object = new DAO();
		db_object.DBConnect();
		String date = db_object.GetSystem_date().getSystem_date();
		float famount = Float.valueOf(amount.trim()).floatValue();
		System.out.println("float amount = " + famount);

		System.out.println("Amount to transfer:" + amount);
		// System.out.println("Account Number:"+from_accno);
		dbconnection obj = new dbconnection();
		System.out.println("Accountnumber1");
		ResultSet rs = obj.selectdata("select balance from transaction where account_number='" + from_accno + "'"
				+ "and transaction_id = (select max(transaction_id) from transaction where account_number ='"
				+ from_accno + "')");
		System.out.println("Accountnumber2");
		ResultSet rs1 = obj.selectdata("select balance from transaction where account_number='" + to_accno + "'"
				+ "and transaction_id = (select max(transaction_id) from transaction where account_number ='" + to_accno
				+ "')");
		System.out.println("Registration1");
		ResultSet rs2 = obj.selectdata("select * from registration where accountnumber='" + to_accno + "'");
		System.out.println("Registration2");
		ResultSet rs4 = obj.selectdata("select accountnumber from registration where accountnumber='" + to_accno + "'");
		try {
			int Aname = 0;
			while (rs4.next()) {
				Aname = rs4.getInt("accountnumber");
				System.out.println("Hname:" + Aname);
			}
			if (to_accno == Aname) {
				if (rs2.next()) {
					String checkname = rs2.getString(1);
					s.setAttribute("name_user", checkname);
					System.out.println("Name1:" + name);
					System.out.println("Checkname1:" + checkname);
					if (name.equals(checkname)) {
						System.out.println("Name1:" + name);
						System.out.println("Checkname1:" + checkname);
						while (rs.next()) {
							// String name = srs.getString("COF_NAME");
							float amount1 = rs.getFloat(1);
							System.out.println("Account Number:" + from_accno);
							System.out.println("Amount1:" + amount1);
							float facc = amount1 - famount;
							if (facc < 0) {
								request.setAttribute("zc", "Cannot transfer Insufficient balance");
								RequestDispatcher dispatch = request
										.getRequestDispatcher("sendfunds.jsp?x=" + recipientid);
								dispatch.forward(request, response);
								break;
							}
							System.out.println("Facc:" + facc);
							while (rs1.next()) {
								// String name = srs.getString("COF_NAME");
								float amount2 = rs1.getFloat(1);
								System.out.println("Account Number:" + to_accno);
								System.out.println("Amount2:" + amount2);
								float toacc = amount2 + famount;
								System.out.println("toacc:" + toacc);
								int trans_id1 = db_object.selectMaxTranID(from_accno) + 1;
								int trans_id2 = db_object.selectMaxTranID(s_to_accno) + 1;
								String db = "db";
								String cr = "cr";
								String desc1 = db_object.selectDesc(s_to_accno) + " " + s_to_accno;
								String desc2 = db_object.selectDesc(from_accno) + " " + from_accno;
								String email_to = db_object.selectEmail(s_to_accno);
								obj.modify("insert into transaction values('" + from_accno + "','" + trans_id1 + "','"
										+ date + "','" + from_accno + "','" + to_accno + "','" + famount + "','" + db
										+ "','" + facc + "','" + desc1 + "')");
								obj.modify("insert into transaction values('" + to_accno + "','" + trans_id2 + "','"
										+ date + "','" + from_accno + "','" + to_accno + "','" + famount + "','" + cr
										+ "','" + toacc + "','" + desc2 + "')");
								String subject1 = famount + "$ has been transeferred from your account to the account: "
										+ to_accno + " Sent from Worldwide Bank";
								String subject2 = famount + "$ has been transeferred to your account from the account: "
										+ from_accno + " Sent from Worldwide Bank";
								SendMail sendemail = new SendMail();
								sendemail.email(subject1, email_from);
								SendMail sendemail1 = new SendMail();
								sendemail1.email(subject2, email_to);
								if (rs2.getString(2).equals("B")) {
									rewardsPoint rewards = new rewardsPoint();
									int ramount = Math.round(famount);
									int flag = rewards.countRewardPoints(ramount, from_accno);
									if(flag == 1){
										System.out.println("Rewards has been added");
									}
									SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
									String temp = db_object.selectDesc(s_to_accno);
									Calendar cal = Calendar.getInstance();
									ResultSet rs_date = obj.selectdata("select * from system_date");
									rs_date.next();
									cal.setTime(rs_date.getDate(1));
									cal.add(Calendar.DATE, 30);
									String new_date = sdf.format(cal.getTime());
									String new_date1 = new_date.replaceAll("/", "-");
									String content = "You have to make a payment for " + temp + "on" + new_date1 + "!!";
									String Yes_No = "No";
									obj.modify(
											"insert into notification (account_number,tran_date,to_acct_number,amount,content,in_auto_pay) values('"
													+ from_accno + "','" + new_date1 + "','" + to_accno + "','"
													+ famount + "','" + content + "','" + Yes_No + "')");
								}
								// obj.modify("update account set Amount='"+
								// facc +"' where Accountnumber='"+ from_accno
								// +"'");
								// obj.modify("update account set Amount='"+
								// toacc +"' where Accountnumber='"+ to_accno
								// +"'");
								System.out.println("Fundtransfer");
								// ResultSet rs3=obj.selectdata("select
								// transfer_id from fundtransfer where
								// transfer_date='"+date+"'");
								// while(rs3.next())
								// {
								// String str = rs3.getString("transfer_id");
								// int transferid = Integer.parseInt(str);
								// System.out.println("transferid:"+transferid);
								request.getRequestDispatcher("transfersummary.jsp").forward(request, response);
								// response.sendRedirect("transfersummary.jsp?x="+transferid);
								// }
							}

						}

						// int transferid = ((Number)
						// rs3.getObject(1)).intValue();

					} else {
						request.setAttribute("zc", "Accountnumber doen't match with name provided");
						RequestDispatcher dispatch = request.getRequestDispatcher("sendfunds.jsp?x=" + recipientid);
						dispatch.forward(request, response);
					}
				}
			} else {
				request.setAttribute("zc", "Accountnumber doesn't exist");
				RequestDispatcher dispatch = request.getRequestDispatcher("sendfunds.jsp?x=" + recipientid);
				dispatch.forward(request, response);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
