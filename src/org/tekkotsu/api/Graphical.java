package org.tekkotsu.api;

import org.eclipse.draw2d.geometry.Rectangle;

public class Graphical {
	
	//Attributes
	private Rectangle shape;
	
	//Constructor
	public Graphical(){
		this.shape = new Rectangle(10,10,100,100);
	}
	
	//Mutators
	public void setShape(Rectangle shape){
		this.shape = shape;
	}
	
	//Accessors
	public Rectangle getShape(){
		return shape;
	}

}
