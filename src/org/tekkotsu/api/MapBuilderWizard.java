package org.tekkotsu.api;

import java.util.ArrayList;

public class MapBuilderWizard {

	//Attributes
	private String name;
	private String map;
	private boolean aprilTag;
	private boolean pursueShapes;
	private ArrayList<MapRequestObject> objects = new ArrayList<MapRequestObject>();
	
	//Constructor
	public MapBuilderWizard(String name, String map, boolean april, boolean pursue){
		this.name = name;
		this.map = map;
		this.aprilTag = april;
		this.objects = new ArrayList<MapRequestObject>();
		this.pursueShapes = pursue;
	}
	
	public MapBuilderWizard(){
		this.name = "";
		this.map = "";
		this.aprilTag = true;
		this.objects = new ArrayList<MapRequestObject>();
		this.pursueShapes = false; //TODO
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
	
	public ArrayList<MapRequestObject> getObjects(){
		return objects;
	}
	
	public boolean getPursueShapes(){
		return pursueShapes;
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
	
	public void setPursueShapes(boolean purs){
		this.pursueShapes = purs;
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
