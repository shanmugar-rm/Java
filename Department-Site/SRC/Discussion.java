package se.term_project;

import java.util.ArrayList;

public class Discussion {
	private int id;
	private String datetime; 
	private int parentfeedid;
	private String user_name;
	private String content;
	private ArrayList<String> child_contents=new ArrayList<String>();
	private ArrayList<String> post_made_by =new ArrayList<String>();
	
	public void setId(int id) {
		this.id = id;
	}
	
	public int getId() {
		return id;
	}
	
	public void setDatetime (String datetime) {
		this.datetime = datetime;
	}
	
	public String getDatetime () {
		return datetime;
	}
	
	public void setParentfeedid (int parentfeedid) {
		this.parentfeedid = parentfeedid;
	}
	
	public int getParentfeedid () {
		return parentfeedid;
	}
	
	public void setUsername (String user_name) {
		this.user_name = user_name;
	}
	
	public String getUsername() {
		return user_name;
	}
	
	public void setContent (String content) {
		this.content = content;
	}
	
	public String getContent() {
		return content;
	}
	
	public void setChildcontent (String child_content) {
		this.child_contents.add(child_content);
	}
	
	public ArrayList<String> getChildcontent () {
		return child_contents;
	}
	
	public void setPostMadeBy (String post_made_by) {
		this.post_made_by.add(post_made_by);
	}
	
	public ArrayList<String> getPostMadeBy () {
		return post_made_by;
	}
}
