package se.term_project;

public class Program_requirements {
	private String concentration;
	private String program;
	private String requirements;
	private String unique_id;
	
	public void setConcentration (String concentration) {
		this.concentration = concentration;
	}
	
	public String getConcentration() {
		return this.concentration;
	}

	public void setProgram(String program) {
		this.program = program;
	}
	
	public String getProgram() {
		return this.program;
	}
	
	public void setRequirement(String requirements) {
		this.requirements = requirements;
	}
	
	public String getRequirements() {
		return this.requirements;
	}
	
	public void setUnique_id (String unique_id) {
		this.unique_id = unique_id;
	}
	
	public String getUnique_id () {
		return this.unique_id;
	}
}
