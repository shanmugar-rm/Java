package se.bank.src;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
//import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class DAO {
	
	Connection con;
	public boolean DBConnect() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/se_bank","root","root");
			return true;
		}
		catch(Exception ex) {
			System.out.println(ex);
			return false;
		}
	}
	
	public boolean DBClose() {
		try {
			con.close();
			return true;
		}
		catch(Exception ex) {
			System.out.println(ex);
			return false;
		}
	}
	
	public system_date GetSystem_date () {
		system_date bank_date = new system_date();
		try {
			String select_max = "Select * from system_date";
			PreparedStatement ps = con.prepareStatement(select_max);
			ResultSet output = ps.executeQuery();
			if (output.next()) {
				bank_date.setSystem_date(output.getString(1));
			}
			return bank_date;
		}
		catch (Exception ex) {
			return bank_date;
		}		
	}
	
	public boolean UpdateSystem_date (String system_date) {
		try {
			String select_max = "update system_date set system_date = ?";
			PreparedStatement ps = con.prepareStatement(select_max);
			ps.setString(1, system_date);
			ps.executeUpdate();
			return true;
		}	
		catch (Exception ex) {
			return false;
		}		
	}
	public RegistrationModel getDetails (String act_no) {
		RegistrationModel user_detail = new RegistrationModel();
		try {
			String select_sql = "select * from registration where accountnumber = ?";
			PreparedStatement ps = con.prepareStatement(select_sql);
			ps.setString(1, act_no);
			ResultSet output = ps.executeQuery();
			while(output.next()) {
				user_detail.setAcct_number(act_no);
				user_detail.setname(output.getString(1));
				user_detail.setemail_id(output.getString(3));
				user_detail.setlogin_id(output.getString(4));
				user_detail.setpassword(output.getString(5));
				user_detail.setphone(output.getString(6));
				user_detail.setaddress(output.getString(7));
				user_detail.setlast_name(output.getString(10));
			}
			return user_detail;
		}
		catch (Exception ex) {
			System.out.println(ex);
			return user_detail;
		}		
	}
	public boolean updateRegistration (String fname, String lname, String email_id, String phone_number, String address, String act_no) {
		try {
			String update_sql = "update registration set name = ?, lastname = ?, emailid = ?, phone=?, address=? where accountnumber =?";
			PreparedStatement ps = con.prepareStatement(update_sql);
			ps.setString(1, fname);
			ps.setString(2, lname);
			ps.setString(3, email_id);
			ps.setString(4, phone_number);
			ps.setString(5, address);
			ps.setString(6, act_no);
			ps.executeUpdate();
			return true;
		}
		catch (Exception ex) {
			System.out.println(ex);
			return false;
		}		
	}
	
	public boolean updatePassword(String password, String act_no) {
		try {
			String update_sql = "update registration set password = ? where accountnumber= ?";
			String update_sql1 = "update act_desc set password = ? where account_number=?";
			PreparedStatement ps = con.prepareStatement(update_sql);
			PreparedStatement ps1 = con.prepareStatement(update_sql1);
			ps.setString(1, password);
			ps.setString(2, act_no);
			ps1.setString(1, password);
			ps1.setString(2, act_no);
			ps.executeUpdate();
			ps1.executeUpdate();
			return true;
		}
		catch (Exception ex) {
			return false;
		}
	}
	
	public boolean updateAuto (int unique_id, String auto) {
		String auto_up = null;
		if (auto.equals("Yes")) {
			auto_up = "No";
		}
		if (auto.equals("No")) {
			auto_up = "Yes";
		}
		try {
			String select_max = "update notification set in_auto_pay = ? where unique_id = ? and in_auto_pay = ?";
			PreparedStatement ps = con.prepareStatement(select_max);
			ps.setString(1, auto);
			ps.setInt(2, unique_id);
			ps.setString(3, auto_up);
			ps.executeUpdate();
			return true;
		}	
		catch (Exception ex) {
			return false;
		}		
	}
	
	public ArrayList<Transaction> GetTransactions (String act_number) {
		ArrayList<Transaction> trans = new ArrayList<Transaction>();
		try {
			String select_st = "Select * from transaction where account_number = (?) order by transaction_date desc, transaction_id desc";
			PreparedStatement ps = con.prepareStatement(select_st);
			ps.setString(1, act_number);
			ResultSet output = ps.executeQuery();
			while (output.next()) {
				Transaction temp = new Transaction();
				temp.setAccountNumber(output.getString(1));
				temp.setTransactionId(output.getInt(2));
				temp.setTransaction_date(output.getString(3));
				temp.setFromAccountNumber(output.getString(4));
				temp.setToAccountNumber(output.getString(5));
				temp.setAmount(output.getFloat(6));
				temp.setTransaction_type(output.getString(7));
				temp.setBalance(output.getFloat(8));
				temp.setDescription(output.getString(9));
				trans.add(temp);
			}
			return trans;
		}
		catch (Exception ex) {
			return trans;
		}
	}
	
	
	public ArrayList<AutoPay> getAllAutoPay_todate (String update_date) {
		ArrayList<AutoPay> autopays = new ArrayList<AutoPay>();
		try {
			String select_st = "Select * from auto_pay where tran_date <= ?";
			PreparedStatement ps = con.prepareStatement(select_st);
			ps.setString(1, update_date);
			ResultSet output = ps.executeQuery();
			while (output.next()) {
				AutoPay temp = new AutoPay();
				temp.setAccountNumber(output.getString(1));
				temp.setTransaction_date(output.getDate(2));
				temp.setToAccountNumber(output.getString(3));
				temp.setAmount(output.getFloat(4));
				temp.setContent(output.getString(5));
				autopays.add(temp);
			}
			return autopays;
		}
		catch (Exception ex) {
			return autopays;
		}
	}
	
	public ArrayList<Notification> GetNotifications (String act_number) {
		ArrayList<Notification> trans = new ArrayList<Notification>();
		try {
			String select_st = "Select * from notification where account_number = (?) order by tran_date";
			PreparedStatement ps = con.prepareStatement(select_st);
			ps.setString(1, act_number);
			ResultSet output = ps.executeQuery();
			while (output.next()) {
				Notification temp = new Notification();
				temp.setUniqueID(output.getInt(1));
				temp.setAccountNumber(output.getString(2));
				temp.setTransaction_date(output.getString(3));
				temp.setToAccountNumber(output.getString(4));
				temp.setAmount(output.getFloat(5));
				temp.setContent(output.getString(6));
				temp.setAuto(output.getString(7));
				trans.add(temp);
			}
			return trans;
		}
		catch (Exception ex) {
			return trans;
		}
	}	
	
	public AutoPay getNotification_unique (int unique_id) {
		AutoPay trans = new AutoPay();
		try {
			String select_st = "Select * from notification where unique_id = (?)";
			PreparedStatement ps = con.prepareStatement(select_st);
			ps.setInt(1, unique_id);
			ResultSet output = ps.executeQuery();
			while (output.next()) {
				trans.setAccountNumber(output.getString(2));
				trans.setTransaction_date(output.getDate(3));
				trans.setToAccountNumber(output.getString(4));
				trans.setAmount(output.getFloat(5));
				trans.setContent(output.getString(6));
			}
			return trans;
		}
		catch (Exception ex) {
			return trans;
		}
	}
	
	public boolean insertAutopay (AutoPay apay) {
		try {
			String insert_sql = "insert into auto_pay values (?,?,?,?,?)";
			PreparedStatement ps = con.prepareStatement(insert_sql);
			ps.setString(1, apay.getAccountNumber());
			ps.setDate(2, apay.getTransaction_date());
			ps.setString(3, apay.getToAccountNumber());
			ps.setFloat(4, apay.getAmount());
			ps.setString(5, apay.getContent());
			ps.executeUpdate();
			return true;
		}
		catch (Exception ex) {
			return false;
		}
	}
	
	public boolean insertTransactionAutopay (AutoPay input) {
		int max_tran_id, new_tran_id;
		Transaction trans = new Transaction();
		float new_amount;
		String desc;
		try {
			max_tran_id = selectMaxTranID(input.getAccountNumber());
			trans = selectTransaction(max_tran_id,input.getAccountNumber());
			desc = selectDesc(input.getToAccountNumber());
			new_amount = trans.getBalance() - input.getAmount();
			new_tran_id = max_tran_id + 1;
			String insert_sql = "insert into transaction values (?,?,?,?,?,?,?,?,?)";
			PreparedStatement ps = con.prepareStatement(insert_sql);
			ps.setString(1, input.getAccountNumber());
			ps.setString(2, String.valueOf(new_tran_id));
			ps.setDate(3, input.getTransaction_date());
			ps.setString(4, input.getAccountNumber());
			ps.setString(5, input.getToAccountNumber());
			ps.setFloat(6, input.getAmount());
			ps.setString(7,"db");
			ps.setFloat(8, new_amount);
			ps.setString(9, desc + " " + input.getToAccountNumber());
			ps.executeUpdate();
			return true;
		}
		catch (Exception ex) {
			return false;
		}
	}
	
	public boolean insertTransactionAutopay_cr (AutoPay input) {
		int max_tran_id, new_tran_id;
		Transaction trans = new Transaction();
		float new_amount;
		String desc;
		try {
			max_tran_id = selectMaxTranID(input.getToAccountNumber());
			trans = selectTransaction(max_tran_id,input.getToAccountNumber());
			desc = selectDesc(input.getAccountNumber());
			new_amount = trans.getBalance() + input.getAmount();
			new_tran_id = max_tran_id + 1;
			String insert_sql = "insert into transaction values (?,?,?,?,?,?,?,?,?)";
			PreparedStatement ps = con.prepareStatement(insert_sql);
			ps.setString(1, input.getToAccountNumber());
			ps.setString(2, String.valueOf(new_tran_id));
			ps.setDate(3, input.getTransaction_date());
			ps.setString(4, input.getToAccountNumber());
			ps.setString(5, input.getToAccountNumber());
			ps.setFloat(6, input.getAmount());
			ps.setString(7,"cr");
			ps.setFloat(8, new_amount);
			ps.setString(9, desc + " " + input.getAccountNumber());
			ps.executeUpdate();
			return true;
		}
		catch (Exception ex) {
			return false;
		}
	}
	
	public boolean updateNotificationAutopay (AutoPay auto_pays) {
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
			Date tran_date = auto_pays.getTransaction_date();
			Calendar cal = Calendar.getInstance();
			cal.setTime(tran_date);
			cal.add(Calendar.DATE, 30);
			String new_date = sdf.format(cal.getTime());
			String new_date1 = new_date.replaceAll("/", "-");
			String content = auto_pays.getContent();
			String temp = String.valueOf(auto_pays.getTransaction_date());
			String content1 = content.replaceAll(temp, new_date1);
			String sql = "update notification set tran_date = ?, content = ? where account_number = ? and tran_date = ? and to_acct_number = ? and amount = ?";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, new_date1);
			ps.setString(2, content1);
			ps.setString(3, auto_pays.getAccountNumber());
			ps.setDate(4, auto_pays.getTransaction_date());
			ps.setString(5, auto_pays.getToAccountNumber());
			ps.setFloat(6, auto_pays.getAmount());
			ps.executeUpdate();
			return true;
		}
		catch (Exception ex) {
			return false;
		}
	}
	
	public boolean updateAutopay (AutoPay autopays) {
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
			Date tran_date = autopays.getTransaction_date();
			Calendar cal = Calendar.getInstance();
			cal.setTime(tran_date);
			cal.add(Calendar.DATE, 30);
			String new_date = sdf.format(cal.getTime());
			String new_date1 = new_date.replaceAll("/", "-");
			String content = autopays.getContent();
			String temp = String.valueOf(autopays.getTransaction_date());
			String content1 = content.replaceAll(temp, new_date1);
			String sql = "update auto_pay set tran_date = ?, content = ? where account_number = ? and tran_date = ? and to_acct_number = ? and amount = ?";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, new_date1);
			ps.setString(2, content1);
			ps.setString(3, autopays.getAccountNumber());
			ps.setDate(4, autopays.getTransaction_date());
			ps.setString(5, autopays.getToAccountNumber());
			ps.setFloat(6, autopays.getAmount());
			ps.executeUpdate();
			return true;
		}
		catch (Exception ex) {
			return false;
		}
	}
	
	public String selectDesc (String act_number) {
		String desc = null;
		try {
			String sql_query = "select act_desc from act_desc where account_number = ?";
			PreparedStatement ps = con.prepareStatement(sql_query);
			ps.setString(1, act_number);
			ResultSet output = ps.executeQuery();
			if (output.next()) {
				desc = output.getString(1);
			}
			return desc;
		}
		catch (Exception ex) {
			return desc;
		}
	}
	
	public String selectEmail (String act_number) {
		String desc = null;
		try {
			String sql_query = "select emailid from registration where accountnumber = ?";
			PreparedStatement ps = con.prepareStatement(sql_query);
			ps.setString(1, act_number);
			ResultSet output = ps.executeQuery();
			if (output.next()) {
				desc = output.getString(1);
			}
			return desc;
		}
		catch (Exception ex) {
			return desc;
		}
	}
	
	public int selectMaxTranID (String act_number) {
		int max_id=1;
		try {
			String sql_query = "select max(transaction_id) from transaction where account_number = ?";
			PreparedStatement ps = con.prepareStatement(sql_query);
			ps.setString(1, act_number);
			ResultSet output = ps.executeQuery();
			if (output.next()) {
				max_id = output.getInt(1);
			}
			return max_id;
		}
		catch (Exception ex) {
			return max_id;
		}
	}
	
	public Transaction selectTransaction (int tran_id, String act_number) {
		Transaction tran = new Transaction();
		try {
			String sql = "select * from transaction where account_number = ? and transaction_id = ?";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, act_number);
			ps.setString(2, String.valueOf(tran_id));
			ResultSet output = ps.executeQuery();
			if (output.next()) {
				tran.setAccountNumber(output.getString(1));
				tran.setTransactionId(output.getInt(2));
				tran.setTransaction_date(output.getString(3));
				tran.setFromAccountNumber(output.getString(4));
				tran.setToAccountNumber(output.getString(5));
				tran.setAmount(output.getFloat(6));
				tran.setTransaction_type(output.getString(7));
				tran.setBalance(output.getFloat(8));
				tran.setDescription(output.getString(9));
			}
			return tran;
		}
		catch (Exception ex) {
			return tran;
		}
	}
	
	public boolean deleteAutopay (AutoPay apay) {
		try {
			String insert_sql = "delete from auto_pay where account_number = ? and tran_date = ? and to_acct_number = ? and amount = ?";
			PreparedStatement ps = con.prepareStatement(insert_sql);
			ps.setString(1, apay.getAccountNumber());
			ps.setDate(2, apay.getTransaction_date());
			ps.setString(3, apay.getToAccountNumber());
			ps.setFloat(4, apay.getAmount());
			ps.executeUpdate();
			return true;
		}
		catch (Exception ex) {
			return false;
		}
	}
	public int getRewardsPoint(String account_number) throws SQLException {
		String sql = "select * from reward_points where account_number='"+account_number+"';";
		Statement st = con.createStatement();
		ResultSet rs = st.executeQuery(sql);
		
		while(rs.next()) {
			return rs.getInt("reward_points");
		}
		
		return 0;
	}

	public boolean checkAccountDetails(String username, String accno, String bank_name) {
		try {
			String select_sql = "select * from make_payment where account_number = ? and act_name = ? and bank_name = ?";
			PreparedStatement ps = con.prepareStatement(select_sql);
			ps.setString(1, accno);
			ps.setString(2, username);
			ps.setString(3, bank_name);			
			ResultSet rs = ps.executeQuery();
			int count = 0; 
			while(rs.next()) {
				count = 1;
			}
			if (count > 0 ){
				return true;
			}
			else{
				return false;
			}
		}
		catch (Exception ex) {
			System.out.println(ex);
			return false;
		}
	}
}
