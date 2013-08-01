package org.tekkotsu.gef;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.gef.DefaultEditDomain;
import org.eclipse.gef.GraphicalViewer;
import org.eclipse.gef.ui.parts.GraphicalEditor;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;
import org.tekkotsu.api.ConstructorCall;
import org.tekkotsu.api.NodeClass;
import org.tekkotsu.api.NodeInstance;

public class StateMachineEditor extends GraphicalEditor {
	
	//ID of the editor
	public static final String ID = "editor.statemachineeditor";
	
	//Constructor
	public StateMachineEditor(){
		
		//Specify the domain for the editor
		setEditDomain(new DefaultEditDomain(this));
		
	}
	
	//Method that creates the related nodeinstance object TODO
	public NodeInstance CreateNodeInstance(){
		
		NodeInstance instance = new NodeInstance(new NodeClass("NodeClass", new ConstructorCall("const")));
		
		instance.setLabel("nodeInstance");
		
		return instance;
		
	}
	
	//TODO
	protected void configureGraphicalViewer(){
		
		super.configureGraphicalViewer();
		GraphicalViewer viewer = getGraphicalViewer();
		viewer.setEditPartFactory(new AppEditPartFactory());
		
	}
	
	//TODO
	protected void initializeGraphicalViewer() {
		GraphicalViewer viewer = getGraphicalViewer();
		viewer.setContents(CreateNodeInstance());
	}


	@Override
	public void doSave(IProgressMonitor monitor) {
		// TODO Auto-generated method stub
		
	}
	

}
