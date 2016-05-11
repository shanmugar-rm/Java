package se.bank.src;

public class Transaction {
	private String account_number;
	private int transaction_id;
	private String transaction_date;
	private String from_account_number;
	private String to_account_number;
	private float amount;
	private String transaction_type;
	private float balance;
	private String description;
	
	public void setAccountNumber (String account_number_ip) {
		this.account_number = account_number_ip;
	}
	
	public String getAccountNumber() {
		return account_number;
	}
	
	public void setDescription (String description_ip) {
		this.description = description_ip;
	}
	
	public String getDescription() {
		return description;
	}
	
	public void setTransactionId (int tran_id) {
		this.transaction_id = tran_id;
	}
	
	public int getTransactionId() {
		return transaction_id;
	}
	
	public void setTransaction_date (String transaction_date_ip) {
		this.transaction_date = transaction_date_ip;
	}
	
	public String getTransaction_date() {
		return transaction_date;
	}
	
	public void setFromAccountNumber (String from_account_number_ip) {
		this.from_account_number = from_account_number_ip;
	}
	
	public String getFromAccountNumber() {
		return from_account_number;
	}
	
	public void setTransaction_type (String transaction_type_ip) {
		this.transaction_type = transaction_type_ip;
	}
	
	public String getTransaction_type() {
		return transaction_type;
	}
	
	public void setAmount(float amt) {
		this.amount = amt;
	}
	
	public float getAmount() {
		return amount;
	}
	
	public void setBalance(float bal) {
		this.balance = bal;
	}
	
	public float getBalance() {
		return balance;
	}
	
	public void setToAccountNumber (String to_account_number_ip) {
		this.to_account_number = to_account_number_ip;
	}
	
	public String getToAccountNumber() {
		return to_account_number;
	}
}
