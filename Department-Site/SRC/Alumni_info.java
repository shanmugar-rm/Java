package se.term_project;

public class Alumni_info {
	private String unique_id;
	private String net_id;
	private String fname;
	private String lname;
	private String current_address;
	private String current_company;
	private String year_passout;	
	
	public void SetUnique_id (String unique_id) {
		this.unique_id = unique_id;
	}
	
	public String getUnique_id () {
		return this.unique_id;
	}
	
	public void Setnet_id (String net_id) {
		this.net_id = net_id;
	}
	
	public String getnet_id () {
		return this.net_id;
	}
	
	public void Setfname (String fname) {
		this.fname = fname;
	}
	
	public String getfname () {
		return this.fname;
	}
	
	public void Setlname (String lname) {
		this.lname = lname;
	}
	
	public String getlname () {
		return this.lname;
	}
	
	public void Setcurrent_address (String current_address) {
		this.current_address = current_address;
	}
	
	public String getcurrent_address () {
		return this.current_address;
	}
	
	public void Setcurrent_company (String current_company) {
		this.current_company = current_company;
	}
	
	public String getcurrent_company () {
		return this.current_company;
	}
	
	public void Setyear_passout (String year_passout) {
		this.year_passout = year_passout;
	}
	
	public String getyear_passout () {
		return this.year_passout;
	}
}
