package org.tekkotsu.api;

public class Behavior {
	
	//Attributes
	private String name;
	private NodeClass nodeClass;
	
	//Constructor
	public Behavior(String name){
		this.name = name;
		this.nodeClass = null;
	}
	
	//Mutator
	public void setName(String name){
		this.name = name;
	}
	
	public void setClass(NodeClass nodeClass){
		this.nodeClass = nodeClass;
	}
	
	//Accessors
	public String getName(){
		return name;
	}
	
	public NodeClass getNodeClass(){
		return nodeClass;
	}
	
	//Operations
	
	//Get the registering behavior code line.
	public String getRegister(){
		
		String reg = "REGISTER_BEHAVIOR(" + getName() +");";
		return reg;
		
	}
	
	
	
}
