package org.tekkotsu.api;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.draw2d.geometry.Rectangle;

public class Graphical extends AbstractModel {
	
	//Attributes
	private Rectangle shape;
	private List<Graphical> children;
	private Graphical parent;
	private PropertyChangeSupport listeners;
	
	//Constructor
	public Graphical(){
		
		this.shape = new Rectangle(10,10,100,100);
		this.parent = null;
		this.children = new ArrayList<Graphical>();
		this.listeners = new PropertyChangeSupport(this);
	}
	
	//Mutators
	public void setShape(Rectangle shape){
		Rectangle oldShape= this.shape;
		this.shape = shape;
		getListeners().firePropertyChange(AbstractModel.PROPERTY_LAYOUT, true, false);
	}

	public void setParent(Graphical parent){
		this.parent = parent;
	}
	
	public boolean addChild(Graphical child){
		//Add the children and keep the boolean in b
		boolean b = this.children.add(child);
		//Check if added
		if(b){
			child.setParent(this);
			getListeners().firePropertyChange(AbstractModel.PROPERTY_ADD, null, child);
		}
		
		//return the b
		return b;
	}
	
	public boolean removeChild(Graphical child){
		//Add the child and keep the boolean
		boolean b = this.children.remove(child);
		
		//Check if removed
		if(b){
			System.out.println("Child removed   " + child);
			getListeners().firePropertyChange(AbstractModel.PROPERTY_REMOVE, true, false); //TODO
			//getListeners().firePropertyChange(new PropertyChangeEvent(child, AbstractModel.PROPERTY_REMOVE, child, null));
		}
		
		//return
		return b;
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

	@Override
	public boolean isPreview() {
		// TODO Auto-generated method stub
		return false;
	}
	
	public void addPropertyChangeListener(PropertyChangeListener listener) {
		listeners.addPropertyChangeListener(listener);
	}

	public PropertyChangeSupport getListeners() {
		return listeners;
	}
	public void removePropertyChangeListener(PropertyChangeListener listener) {
		listeners.removePropertyChangeListener(listener);
	}
	
}
