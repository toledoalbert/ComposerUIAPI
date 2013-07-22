package org.tekkotsu.api;

public class MapRequestObject {

	//Attributes
	private String shape;
	private String color;
	
	//Constructor
	public MapRequestObject(String shape, String color){
		this.shape = shape;
		this.color = color;
	}
	
	public MapRequestObject(){
		this.shape = "";
		this.color = "";
	}
	
	//Mutator methods
	public void setColor(String color){
		this.color = color;
	}
	
	public void setShape(String shape){
		this.shape = shape;
	}
	
	//Accessor methods
	public String getColor(){
		return color;
	}
	
	public String getShape(){
		return shape;
	}
	
}
