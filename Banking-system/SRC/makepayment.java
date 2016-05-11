package se.bank.src;

import java.io.IOException;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class makepayment
 */
public class makepayment extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public makepayment() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//doGet(request, response);
		try {
			HttpSession session = request.getSession();
			String accno = request.getParameter("accno");
			String amount = request.getParameter("amount");
			String username = request.getParameter("username");
			String desc = request.getParameter("desc");
			String bank_name = request.getParameter("bank_name");
			String from_account = (String) session.getAttribute("acct_number");
			System.out.println(from_account);
			System.out.println(username);
			System.out.println(amount);
			System.out.println(accno);
			DAO dao = new DAO();
			dao.DBConnect();
			boolean checkaccount = dao.checkAccountDetails(username, accno, bank_name);
			if (checkaccount) {
				float famount = Float.valueOf(amount.trim()).floatValue();
				dbconnection obj = new dbconnection();
				ResultSet rs = obj.selectdata("select balance from transaction where account_number='" + from_account + "'"
						+ "and transaction_id = (select max(transaction_id) from transaction where account_number ='"
						+ from_account + "')");
				float amount1 = 0;
				while (rs.next()) {
					amount1 = rs.getFloat(1);
				}				
				float facc = amount1 - famount;
				if (facc < 0) {
					request.setAttribute("zc", "Cannot transfer Insufficient balance");
					request.getRequestDispatcher("fundtransfer.jsp").forward(request, response);				
				}
				int trans_id1 = dao.selectMaxTranID(from_account) + 1;
				String date = dao.GetSystem_date().getSystem_date();
				String db = "db";
				obj.modify("insert into transaction values('" + from_account + "','" + trans_id1 + "','"
						+ date + "','" + from_account + "','" + accno + "','" + famount + "','" + db
						+ "','" + facc + "','" + username + "')");
				//String subject1 = famount + "$ has been transeferred from your account to the account: "
				//		+ accno + " Sent from Worldwide Bank";
				//SendMail sendemail = new SendMail();
				//sendemail.email(subject1, email_from);
				rewardsPoint rewards = new rewardsPoint();
				int ramount = Math.round(famount);
				int flag = rewards.countRewardPoints(ramount, from_account);
				if(flag == 1){
					System.out.println("Rewards has been added");
				}
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
				String temp = username;
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
								+ from_account + "','" + new_date1 + "','" + accno + "','"
								+ famount + "','" + content + "','" + Yes_No + "')");
				HttpSession s = request.getSession();
				s.setAttribute("name_user", username);
				s.setAttribute("desc_transfer", desc);
				request.getRequestDispatcher("transfersummary.jsp").forward(request, response);
			}
			else {
				request.setAttribute("zc", "Account number doesn't exist");
				request.getRequestDispatcher("fundtransfer.jsp").forward(request, response);
			}
		}
		catch (Exception ex) {
			System.out.println(ex);
		}
	}
}
