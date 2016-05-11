package se.bank.src;

import java.sql.Date;

public class AutoPay {
	private String account_number;
	private Date transaction_date;
	private String to_account_number;
	private float amount;
	private String content;
	
	public void setAccountNumber (String account_number_ip) {
		this.account_number = account_number_ip;
	}
	
	public String getAccountNumber() {
		return account_number;
	}
	
	public void setTransaction_date (Date transaction_date_ip) {
		this.transaction_date = transaction_date_ip;
	}
	
	public Date getTransaction_date() {
		return transaction_date;
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
	
	public void setContent (String content_ip) {
		this.content = content_ip;
	}
	
	public String getContent() {
		return content;
	}
}
