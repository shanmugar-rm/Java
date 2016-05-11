package se.term_project;

public class Suggestion {
	private String concentration;
	private String suggested_courses;
	private String unique_id;
	
	public void setconcentration (String concentration) {
		this.concentration = concentration;
	}
	
	public String getconcentration () {
		return this.concentration;
	}
	
	public void setSuggested_courses (String suggested_courses) {
		this.suggested_courses = suggested_courses;
	}
	
	public String getSuggested_courses () {
		return this.suggested_courses;
	}
	
	public void setUnique_id (String unique_id) {
		this.unique_id = unique_id;
	}
	
	public String getUnique_id () {
		return this.unique_id;
	}
}
