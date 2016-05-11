package se.term_project;

import java.sql.Date;

public class ResourseHold {
	private String user_name;
    private String resourse_name;
    private Date reserve_date;
    private int reserved_slot;
    private int unique_id;
    private String fname;
    
    public void setUser_name (String user_name) {
 		this.user_name = user_name;
 	}
 		
 	public String getUser_name() {
 		return user_name;
 	}
 	
 	public void setfname (String fname) {
 		this.fname = fname;
 	}
 		
 	public String getfname() {
 		return fname;
 	}
    
    public void setResourse_name (String resourse_name) {
 		this.resourse_name = resourse_name;
 	}
 		
 	public String getResourse_name() {
 		return resourse_name;
 	}
 	
    public void setReserve_date (Date reserve_date) {
 		this.reserve_date = reserve_date;
 	}
 		
 	public Date getReserve_date() {
 		return reserve_date;
 	}
 	
 	public void setReserveSlot (int reserverslot) {
 		this.reserved_slot = reserverslot;
 	}
 	
 	public int getReserverSlot () {
 		return reserved_slot;
 	}

 	public void setUnique_id (int unique_id) {
 		this.unique_id = unique_id;
 	}
 	
 	public int getUnique_id () {
 		return unique_id;
 	}
}
