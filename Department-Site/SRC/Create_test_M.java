package se.term_project;

public class Create_test_M {
	private String unique_id;
	private String test_name;
	private String tested_created_by;
	private String test_date;
	private String test_details;
	
	public void setUnique_id (String unique_id) {
		this.unique_id = unique_id;
	}
	
	public String getUnique_id () {
		return this.unique_id;
	}

	public void settest_name (String test_name) {
		this.test_name = test_name;
	}
	
	public String gettest_name () {
		return this.test_name;
	}
	
	public void settested_created_by (String tested_created_by) {
		this.tested_created_by = tested_created_by;
	}
	
	public String gettested_created_by () {
		return this.tested_created_by;
	}
	
	public void settest_date (String test_date) {
		this.test_date = test_date;
	}
	
	public String gettest_date () {
		return this.test_date;
	}
	
	public void settest_details (String test_details) {
		this.test_details = test_details;
	}
	
	public String gettest_details () {
		return this.test_details;
	}
}
