package org.tekkotsu.gef;

import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IPersistableElement;

public class StateMachineEditorInput implements IEditorInput {
	
	public String name = null;
	
	//Constructor setting the name
	public StateMachineEditorInput(String name) {
		this.name = name;
	}

	@Override
	//method to check if it exists
	public boolean exists() {
		return (this.name != null);
	}
	
	//classic equals method
	public boolean equals(Object o) {
		if (!(o instanceof StateMachineEditorInput))
			return false;
		return ((StateMachineEditorInput)o).getName().equals(getName());
	}

	@Override
	public ImageDescriptor getImageDescriptor() {
		return ImageDescriptor.getMissingImageDescriptor();
	}

	@Override
	//Method to get name
	public String getName() {
		return this.name;
	}

	@Override
	public IPersistableElement getPersistable() {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public String getToolTipText() {
		return this.name;
	}
	
	@Override
	public Object getAdapter(Class adapter) {
		// TODO Auto-generated method stub
		return null;
	}
}
