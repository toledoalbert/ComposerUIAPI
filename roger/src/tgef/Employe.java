package tgef;

public class Employe extends Node {
	
	private String prenom;
	
	//Static name field 
	public static final String PROPERTY_FIRSTNAME = "EmployePrenom";

	
	public void setPrenom(String prenom) {
	this.prenom = prenom;
	}

	public String getPrenom() {
	return this.prenom;
	}


}
