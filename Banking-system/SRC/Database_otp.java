package se.bank.src;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

public class Database_otp {
	private int num;
	private String email;
	private Connection conn;

	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Connection getConn() {
		return conn;
	}

	public void setConn(Connection conn) {
		this.conn = conn;
	}

	public Database_otp() {
		super();
	}

	public void add(int num,String Email,String username, Connection conn) throws SQLException {
		
		String obj2 = String.valueOf(num);

		//String Email = "jadhavashish41@yahoo.in";
		String sql = "INSERT INTO otp (loginid,email,otp) VALUES (?,?,?);";

		PreparedStatement stmt = conn.prepareStatement(sql);

		stmt.setString(1, username);
		stmt.setString(2, Email);
		stmt.setInt(3, num);

		Integer rs = stmt.executeUpdate();

		if (rs != 0) {
			// message = "Updated successfully";
		} else {
			// message = "Updatation failed";
		}

		stmt.close();

	}

	public boolean search(int num,String email,String username, Connection conn) throws SQLException {

		//String email = "jadhavashish41@yahoo.in";
		int otp = 0;
		String sql = "select * from `otp` where email=? and loginid=?;";

		PreparedStatement stmt = conn.prepareStatement(sql);

		stmt.setString(1, email);
		stmt.setString(2, username);
		// stmt.setInt(2, num);

		ResultSet rs = stmt.executeQuery();

		if (rs.next()) {
			otp = rs.getInt("otp");

		}

		stmt.close();
		System.out.println("OTP is");
		System.out.println(otp);
		String obj2 = String.valueOf(num);
		// System.out.println("hashCode for an integer is " + obj1.hashCode());
		//System.out.println("hashCode for a string is " + obj2.hashCode());

		if (otp == num) {
			return true;
		} else {
			return false;
		}

	}

	public void remove(int otp,String email,String username, Connection conn) throws SQLException {

		//String email = "jadhavashish41@yahoo.in";
		// int otp = 0;
		//String sql = "UPDATE otp SET otp = ? where email = ? and loginid = ?;";
		String sql = "delete from `otp` where email=? and loginid=?;";
		//UPDATE Customers
		//SET ContactName='Alfred Schmidt', City='Hamburg'
		//		WHERE CustomerName='Alfreds Futterkiste';
		PreparedStatement stmt = conn.prepareStatement(sql);

		//stmt.setInt(1, otp);
		stmt.setString(1, email);
		stmt.setString(2, username);
		
		// stmt.setInt(2, num);

		Integer rs = stmt.executeUpdate();

		stmt.close();

	}

}
