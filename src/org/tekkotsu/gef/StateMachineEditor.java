package org.tekkotsu.gef;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.gef.DefaultEditDomain;
import org.eclipse.gef.ui.parts.GraphicalEditor;

public class StateMachineEditor extends GraphicalEditor {
	
	//ID of the editor
	public static final String ID = "editor.statemachineeditor";
	
	public StateMachineEditor(){
		
		//Specify the domain for the editor
		setEditDomain(new DefaultEditDomain(this));
		
	}

	@Override
	protected void initializeGraphicalViewer() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void doSave(IProgressMonitor monitor) {
		// TODO Auto-generated method stub
		
	}
	

}
