package se.term_project;

import java.util.Date;

public class Announcement {
	private String user_name;
	private String announcement_type;
	private Date announcement_date;
	private String announcement_content;
	private String announcement_heading;
	private String unique_id;
	
    public void setUser_name (String user_name) {
		this.user_name = user_name;
	}
		
	public String getUser_name() {
		return user_name;
	}
	
	public void setAnnouncement_type (String announcement_type) {
		this.announcement_type = announcement_type;
	}
		
	public String getAnnouncement_type() {
		return announcement_type;
	}	
	
	public void setAnnouncement_date (Date announcement_date) {
		this.announcement_date = announcement_date;
	}
		
	public Date getAnnouncement_date() {
		return announcement_date;
	}
	
	public void setAnnouncement_content (String announcement_content) {
		this.announcement_content = announcement_content;
	}
		
	public String getAnnouncement_content() {
		return announcement_content;
	}	
	
	public void setAnnouncement_heading (String announcement_heading) {
		this.announcement_heading = announcement_heading;
	}
		
	public String getAnnouncement_heading() {
		return announcement_heading;
	}	
	
	public void setUnique_id (String unique_id) {
		this.unique_id = unique_id;
	}
		
	public String getUnique() {
		return unique_id;
	}	
}
