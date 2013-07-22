package org.tekkotsu.api;

import java.util.ArrayList;

public class MapBuilder {

	//Attributes
	private String name;
	private String map;
	private boolean aprilTag;
	private ArrayList<MapRequestObject> objects = new ArrayList<MapRequestObject>();
	
	//Constructor
	public MapBuilder(String name, String map, boolean april){
		this.name = name;
		this.map = map;
		this.aprilTag = april;
		this.objects = new ArrayList<MapRequestObject>();
	}
	
	public MapBuilder(){
		this.name = "";
		this.map = "";
		this.aprilTag = true;
		this.objects = new ArrayList<MapRequestObject>();
	}
	
	//Accessor methods
	public String getName(){
		return name;
	}
	
	public String getMap(){
		return map;
	}
	
	public boolean getAprilTag(){
		return aprilTag;
	}
	
	
	//Mutators
	public void setName(String name){
		this.name = name;
	}
	
	public void setMap(String map){
		this.map = map;
	}
	
	public void setAprilTag(boolean april){
		this.aprilTag = april;
	}
	
	
	//Adding and removing requests
	public void addRequestObject(MapRequestObject obj){
		this.objects.add(obj);
	}
	
	public void removeRequestObject(MapRequestObject obj){
		this.objects.remove(obj);
	}
	
	public void removeRequestObject(int i){
		this.objects.remove(i);
	}
	
	
}