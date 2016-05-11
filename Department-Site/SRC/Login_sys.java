package se.term_project;

public class Login_sys {
     private String user_name;
     private String password_sys;
     private String first_name;
     private String last_name;
     private String advisor_name;
     private String program_taken;
     private String student_employee;
     private String email_id;
     private String year_joined;
     private String major;
     private String phone;
     
    public void setUser_name (String user_name) {
		this.user_name = user_name;
	}
		
	public String getUser_name() {
		return user_name;
	}
	
	public void setphone (String phone) {
		this.phone = phone;
	}
		
	public String getphone() {
		return phone;
	}
	
	public void setmajor (String major) {
		this.major = major;
	}
		
	public String getmajor() {
		return major;
	}
	
	public void setyear_joined (String year_joined) {
		this.year_joined = year_joined;
	}
		
	public String getyear_joined() {
		return year_joined;
	}
	
    public void setPassword_sys (String password_sys) {
		this.password_sys = password_sys;
	}
		
	public String getpassword_sys() {
		return password_sys;
	}
	
    public void setFirst_name (String first_name) {
		this.first_name = first_name;
	}
		
	public String getFirst_name() {
		return first_name;
	}
	
    public void setLast_name (String last_name) {
		this.last_name = last_name;
	}
		
	public String getLast_name() {
		return last_name;
	}
	
    public void setAdvisor_name (String advisor_name) {
		this.advisor_name = advisor_name;
	}
		
	public String getAdvisor_name() {
		return advisor_name;
	}

    public void setProgram_taken (String program_taken) {
		this.program_taken = program_taken;
	}
		
	public String getProgram_taken() {
		return program_taken;
	}

    public void setStudent_employee (String student_employee) {
		this.student_employee = student_employee;
	}
		
	public String getStudent_employee() {
		return student_employee;
	}
	
    public void setemail_id (String email_id) {
		this.email_id = email_id;
	}
		
	public String getemail_id() {
		return email_id;
	}
}
