package se.term_project;

public class Phd_status {
	private String unique_id;
	private String net_id;
	private String year_joined;
	private String is_candidate;
	private String first_name;
	private String last_name;
	
	public void setUnique_id(String unique_id) {
		this.unique_id = unique_id;
	}
	
	public String getUnique_id() {
		return unique_id;
	}
	
	public void setnet_id(String net_id) {
		this.net_id = net_id;
	}
	
	public String getnet_id() {
		return net_id;
	}

	public void setyear_joined(String year_joined) {
		this.year_joined = year_joined;
	}
	
	public String getyear_joined() {
		return year_joined;
	}
	
	public void setis_candidate (String is_candidate) {
		this.is_candidate = is_candidate;
	}
	
	public String getis_candidate() {
		return is_candidate;
	}
	
	public void setfirst_name (String first_name) {
		this.first_name = first_name;
	}
	
	public String getfirst_name() {
		return first_name;
	}
	
	public void setlast_name (String last_name) {
		this.last_name = last_name;
	}
	
	public String getlast_name() {
		return last_name;
	}
}

