package org.tekkotsu.api;

public class Parameter {
	//attributes
	private String type;
	private String value;
	private String helpText;
	private boolean optional;
	
	//Constructor
	public Parameter(String type, String value, boolean optional, String helpText){
		this.type = type;
		this.value = value;
		this.helpText = helpText;
		this.optional = optional;
	}
	
	//Constructor for quick parameter creation.
	public Parameter(String type, String value){
		this.type = type;
		this.value = value;
		this.helpText = "Enter parameter value here.";
		this.optional = true;
	}
	



	
	//Accessor methods
	public String getType(){
		return type;
	}
	
	public String getValue(){
		return value;
	}

	public String getHelpText(){
		return helpText;
	}
	
	public boolean ifOptional(){
		return optional;
	}
	




	//Mutator methods
	public void setType(String type){
		this.type = type;
	}
	
	public void setValue(String value){
		this.value = value;
	}

	public void setHelpText(String helpText){
		this.helpText = helpText;
	}
	
	public void setIfOptional(boolean optional){
		this.optional = optional;
	}

}
