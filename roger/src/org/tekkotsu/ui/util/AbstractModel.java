/*
 * Created on Sep 12, 2004
 */
package org.tekkotsu.ui.util;

/**
 * AbstractModel containing property control methods
 * @author asangpet
 */

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.beans.PropertyChangeSupport;
import java.util.List;

import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.ui.views.properties.IPropertyDescriptor;
import org.eclipse.ui.views.properties.IPropertySource;

import tgef.LayoutData;
import tgef.Node;
import tgef.NodePropertySource;

/*
 * Jdom code
 * import org.jdom.Content;
 */

abstract public class AbstractModel implements IAdaptable {
	public PropertyChangeSupport props = new PropertyChangeSupport(this);
	private AbstractModel parent;
	private List<AbstractModel> children; 
	//Activate events when GEditor properties are changed
	private PropertyChangeSupport listeners;
	//for property sheets
	private IPropertySource propertySource = null;
	
	//Properties
	public static final String PROPERTY_LAYOUT = "NodeLayout";
	public static final String PROPERTY_ADD = "NodeAddChild";
	public static final String PROPERTY_REMOVE = "NodeRemoveChild";
	public static final String PROPERTY_RENAME = "NodeRename";
	
	public static final String PROPERTIES_MODEL_CATEGORY = "Info";
    public static final String PROPERTIES_APPEARANCE_CATEGORY = "Display";
    
    private String name;
    
    public void setName(String name){
    	this.name=name;
    }
    
    public String getName(){
    	return this.name;
    }
	
	public void setParent(AbstractModel parent) {
		this.parent = parent;
	}
	
	public AbstractModel getParent2() {
		return parent;
	}
	
	public Object getEditableValue() {
		return this;
	}
	
	public Object getPropertyValue(Object id) {
		return null;
	}
	
	public boolean isPropertySet(Object id) {
		return false;
	}
	
	public IPropertyDescriptor[] getPropertyDescriptors() {
		return new IPropertyDescriptor[0];
	}
	
	public void resetPropertyValue(Object id) {
		//default of do nothing
	}
	public void setPropertyValue(Object id, Object value) {
		//default of do nothing
	}
	
	public void doPreDelete(LayoutData contents)
    {
    	//nothing by default
    }
	
	public List<AbstractModel> getChildrenArray() { 
		return this.children; 
		
	}
	/**
	 * Generate XML code representation of this model
	 * @return
	 */
	
	/*
	 * Jdom code
	 * abstract public Content getXML();
	 */
	/*@Override
	public Object getAdapter(Class adapter) {
		if (adapter == IPropertySource.class) { 
			if (propertySource == null) 
			     propertySource = new NodePropertySource(this); 
			return propertySource; 
			                                   } 
			return null; 
			} */

	
	abstract public boolean isPreview();
	
	/*public void activate() {
		props.firePropertyChange(Node.P_ACTIVATE, null, null);
	}
	public void deactivate() {
		props.firePropertyChange(Node.P_DEACTIVATE, null, null);
	}
*/
	public void doPostAdd() {
		// TODO Auto-generated method stub
		
	}
	//Just added this TODO test
	@Override
	public Object getAdapter(Class adapter) {
		if (adapter == IPropertySource.class) { 
			if (propertySource == null) 
			propertySource = new NodePropertySource(this); 
			return propertySource; 
			} 
			return null; 
			} 

	
	//To change listeners
	//Property Change Listener
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
