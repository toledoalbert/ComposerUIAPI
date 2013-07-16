package org.tekkotsu.api;

import java.util.ArrayList;

public class Method {
	
	//Attributes
	private String name;
	private String body;
	private ArrayList<Parameter> parameters;
	private NodeClass parentClass;
	
	//Constructor
	public Method (String name, NodeClass parentClass){
		this.name = name;
		body = "";
		parameters = new ArrayList<Parameter>();
		this.parentClass = parentClass;
	}
	
	//Mutator methods
	public void setName(String name){
		this.name = name;
	}
	
	public void setBody(String body){
		this.body = body;
	}
	
	public void setParameters(ArrayList<Parameter> parameters){
		this.parameters = parameters;
	}
	
	public void addParameter(Parameter parameter){
		parameters.add(parameter);
	}
	
	public void removeParameter(Parameter parameter){
		parameters.remove(parameter);
	}
	
	public void removeParameter(int index){
		parameters.remove(index);
	}
	
	public void setParentClass(NodeClass parentClass){
		this.parentClass = parentClass;
	}
	
	//Accessor methods
	public String getName(){
		return name;
	}
	
	public String getBody(){
		return body;
	}
	
	public ArrayList<Parameter> getParameters(){
		return parameters;
	}
	
	public Parameter getParameter(int i){
		return parameters.get(i);
	}
	
	public NodeClass getParentClass(){
		return parentClass;
	}
	
	
	
}
