package org.tekkotsu.api;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.draw2d.geometry.Rectangle;

public class Graphical {
	
	//Attributes
	private Rectangle shape;
	private List<Graphical> children;
	private Graphical parent;
	
	//Constructor
	public Graphical(){
		this.shape = new Rectangle(10,10,100,100);
		this.parent = null;
		this.children = new ArrayList<Graphical>();
	}
	
	//Mutators
	public void setShape(Rectangle shape){
		this.shape = shape;
	}
	
	public void setParent(Graphical parent){
		this.parent = parent;
	}
	
	public boolean addChild(Graphical child){
		child.setParent(this);
		return this.children.add(child);
	}
	
	public boolean removeChild(Graphical child){
		return this.children.remove(child);
	}
	
	//Accessors
	public Rectangle getShape(){
		return shape;
	}
	
	public Graphical getParent(){
		return this.parent;
	}
	
	public List<Graphical> getChildren(){
		return this.children;
	}

}
