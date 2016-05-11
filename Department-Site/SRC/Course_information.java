package se.term_project;

import java.sql.Blob;

public class Course_information {
    private String course_id;
    private String course_title;
    private String unique_id;
    private String TA_name;
    private String TA_email_id;
    private String TA_office_location;
    private String user_id;
    private String user_name;
    private String office_hours;
    private String office_location;
    private String email_id;
    private Blob Syllabus;
    
    public void setCourse_id (String course_id) {
		this.course_id = course_id;
	}
		
	public String getCourse_id() {
		return course_id;
	}
	
    public void setTA_name (String TA_name) {
		this.TA_name = TA_name;
	}
		
	public String getTA_name() {
		return TA_name;
	}
	
    public void setTA_email_id (String TA_email_id) {
		this.TA_email_id = TA_email_id;
	}
		
	public String getTA_email_id() {
		return TA_email_id;
	}
	
    public void setTA_office_location (String TA_office_location) {
		this.TA_office_location = TA_office_location;
	}
		
	public String getTA_office_location() {
		return TA_office_location;
	}
	
    public void setCourse_title (String course_title) {
		this.course_title = course_title;
	}
		
	public String getCourse_title() {
		return course_title;
	}
	
    public void setUnique_id (String unique_id) {
		this.unique_id = unique_id;
	}
		
	public String getUnique_id() {
		return unique_id;
	}
	
	
    public void setUser_id (String user_id) {
		this.user_id = user_id;
	}
		
	public String getUser_id() {
		return user_id;
	}
	
    public void setUser_name (String user_name) {
		this.user_name = user_name;
	}
		
	public String getUser_name() {
		return user_name;
	}
	
    public void setOffice_hours (String office_hours) {
		this.office_hours = office_hours;
	}
		
	public String getOffice_hours() {
		return office_hours;
	}
	
    public void setOffice_location (String office_location) {
		this.office_location = office_location;
	}
		
	public String getOffice_location() {
		return office_location;
	}
	
    public void setEmail_id (String email_id) {
		this.email_id = email_id;
	}
		
	public String getEmail_id() {
		return email_id;
	}
	
    public void setSyllabus (Blob Syllabus) {
		this.Syllabus = Syllabus;
	}
		
	public Blob getSyllabus() {
		return Syllabus;
	}
    
}
