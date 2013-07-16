package org.tekkotsu.api;

public class Variable {

	//Attributes
	private String type;
	private String value;
	
	//Constructor
	public Variable(String type, String value){
		this.type = type;
		this.value = value;
	}
	
	//Mutators
	public void setType(String type){
		this.type = type;
	}
	
	public void setValue(String value){
		this.value = value;
	}
	
	//Accessor
	public String getType(){
		return type;
	}
	
	public String getValue(){
		return value;
	}
	
	
	
}
