package se.term_project;

public class Student_test {
	private String unique_id;
	private String parentid;
	private String student_fname;
	private String student_lname;
	private float student_mark;
	
	public void setUnique_id (String unique_id) {
		this.unique_id = unique_id;
	}
	
	public String getUnique_id () {
		return this.unique_id;
	}
	
	public void setparentid (String parentid) {
		this.parentid = parentid;
	}
	
	public String getparentid () {
		return this.parentid;
	}

	public void setstudent_fname (String student_fname) {
		this.student_fname = student_fname;
	}
	
	public String getstudent_fname () {
		return this.student_fname;
	}
	
	public void setstudent_lname (String student_lname) {
		this.student_lname = student_lname;
	}
	
	public String getstudent_lname () {
		return this.student_lname;
	}
	
	public void setstudent_mark (float student_mark) {
		this.student_mark = student_mark;
	}
	
	public float getstudent_mark () {
		return this.student_mark;
	}
}
