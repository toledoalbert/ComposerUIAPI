package org.tekkotsu.api;

import java.util.ArrayList;

//Node class for the Tekkotsu State Machine Composer
//Author: Albert Berk Toledo
//Start date: 06/17/2013


//Class Header
public class TransitionClass {

	//Attributes
	private String name;						//Name of the Transition
	private String definition;					//Definition
	private String color;						//COlor
	private ConstructorCall constructor; 		//Constructor
	
	//Constructor
	public TransitionClass(String name, String definition, String color, ConstructorCall constructor){
		
		this.name = name;
		this.definition = definition;
		this.color = color;
		this.constructor = constructor;
		
	}
	
	public TransitionClass(String name, ConstructorCall constructor){
		this.name = name;
		this.constructor = constructor;
		this.definition = "My new transition class.";
		this.color = "#fffff";		//TODO	default color to be decided.
	}
	



	//Accessor methods
	public String getName(){
		return name;
	}
	
	public String getColor(){
		return color;
	}
	
	public String definition(){
		return definition;
	}
	
	public ArrayList<Parameter> getParameters(){
		return constructor.getParameters();
	}
	
	public int getNumOfParameters(){
		return this.constructor.getParameters().size();
	}
	
	public ConstructorCall getConstructor(){
		return constructor;
	}
	




	//Mutator methods
	public void setName(String name){
		this.name = name;
	}
	
	public void setColor(String color){
		this.color = color;
	}
	
	public void setDefinition(String definition){
		this.definition = definition;
	}
	
	public void setParameters(ArrayList<Parameter> parameters){
		this.constructor.setParameters(parameters);
	}
	
	public void setConstructor(ConstructorCall constructor){
		this.constructor = constructor;
	}
	




	//Single element mutator methods.
	public void addParameter(Parameter parameter){
		this.constructor.addParameter(parameter);
	}
	
	public void removeParameter(Parameter parameter){
		this.constructor.removeParameter(parameter);
	}
	
	//TODO return instance from a transitionclass object (just for convenience)
		
		  
		   	public TransitionInstance makeInstance(){
		   		TransitionInstance instance = new TransitionInstance(this);
		   		return instance;
		   	}
		  	
		 
}
