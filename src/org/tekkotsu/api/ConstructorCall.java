package org.tekkotsu.api;

import java.util.ArrayList;

public class ConstructorCall {
	
	//Attributes
	private String name;
	private ArrayList<Parameter> parameters;
	
	//Constructor
	public ConstructorCall(String name, ArrayList<Parameter> parameters){
		this.name = name;
		this.parameters = parameters;
	}
	
	//Constructor that will construct with only a name. To add parameters later.
	public ConstructorCall(String name){
		this.name = name;
		this.parameters = new ArrayList<Parameter>();
	}
	
	//Mutator methods
	public void setName(String name){
		this.name = name;
	}
	
	public void setParameters(ArrayList<Parameter> parameters){
		this.parameters = parameters;
	}
	
	
	//Accessor methods
	public String getName(){
		return name;
	}
	
	public ArrayList<Parameter> getParameters(){
		return parameters;
	}
	
	
	//Mutators for single elements.
	public void addParameter(Parameter parameter){
		this.parameters.add(parameter);
	}
	
	public void removeParameter(Parameter parameter){
		this.parameters.remove(parameter);
	}	

}
