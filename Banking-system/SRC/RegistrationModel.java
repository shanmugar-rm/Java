package se.bank.src;

public class RegistrationModel {
	private String acct_number;
	private String email_id;
	private String name;
	private String login_id;
	private String password;
	private String phone;
	private String address;
	private String last_name;
	
	public void setAcct_number (String act_number) {
		this.acct_number = act_number;
	}
	
	public String getAcct_number () {
		return this.acct_number;
	}
	
	public void setemail_id (String email_id) {
		this.email_id = email_id;
	}
	
	public String getemail_id () {
		return this.email_id;
	}
	
	public void setname (String name) {
		this.name = name;
	}
	
	public String getname () {
		return this.name;
	}
	
	public void setlogin_id (String login_id) {
		this.login_id = login_id;
	}
	
	public String getlogin_id () {
		return this.login_id;
	}
	
	public void setpassword (String password) {
		this.password = password;
	}
	
	public String getpassword () {
		return this.password;
	}
	
	public void setphone (String phone) {
		this.phone = phone;
	}
	
	public String getphone () {
		return this.phone;
	}
	
	public void setaddress (String address) {
		this.address = address;
	}
	
	public String getaddress () {
		return this.address;
	}
	
	public void setlast_name (String last_name) {
		this.last_name = last_name;
	}
	
	public String getlast_name () {
		return this.last_name;
	}
}
