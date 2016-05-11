package se.bank.src;

public class Notification {
	private int unique_id;
	private String account_number;
	private String transaction_date;
	private String to_account_number;
	private float amount;
	private String content;
	private String auto_y_n;
	
	public void setUniqueID (int uni_id) {
		this.unique_id = uni_id;
	}
	
	public int getUniqueID() {
		return unique_id;
	}
	
	public void setAuto (String auto_ip) {
		this.auto_y_n = auto_ip;
	}
	
	public String getAuto() {
		return auto_y_n;
	}
	
	public void setAccountNumber (String account_number_ip) {
		this.account_number = account_number_ip;
	}
	
	public String getAccountNumber() {
		return account_number;
	}
	
	public void setTransaction_date (String transaction_date_ip) {
		this.transaction_date = transaction_date_ip;
	}
	
	public String getTransaction_date() {
		return transaction_date;
	}
	
	public void setContent (String content_ip) {
		this.content = content_ip;
	}
	
	public String getContent() {
		return content;
	}
	
	public void setAmount(float amt) {
		this.amount = amt;
	}
	
	public float getAmount() {
		return amount;
	}
	
	public void setToAccountNumber (String to_account_number_ip) {
		this.to_account_number = to_account_number_ip;
	}
	
	public String getToAccountNumber() {
		return to_account_number;
	}

}
