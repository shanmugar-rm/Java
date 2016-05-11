package se.term_project;

public class Student_status {
	private String unique_id;
	private String net_id;
	private String fname;
	private String core_course_status;
	private String elective_status;
	private String intern_status;
	
	public void setUnique_id (String unique_id) {
		this.unique_id = unique_id;
	}
	
	public String getUnique_id () {
		return this.unique_id;
	}
	
	public void setNet_id (String net_id) {
		this.net_id = net_id;
	}
	
	public String getNet_id () {
		return this.net_id;
	}
	
	public void setfname (String fname) {
		this.fname = fname;
	}
	
	public String getfname () {
		return this.fname;
	}
	
	public void setCore_course_status(String core_course_status) {
		this.core_course_status = core_course_status;
	}
	
	public String getCore_course_status() {
		return this.core_course_status;
	}
	
	public void setelective_status (String elective_status) {
		this.elective_status = elective_status;
	}
	
	public String getelective_status() {
		return this.elective_status;
	}
	
	public void setintern_status(String intern_status) {
		this.intern_status = intern_status;
	}
	
	public String getintern_status() {
		return this.intern_status;
	}
}
