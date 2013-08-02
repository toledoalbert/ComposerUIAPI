package org.tekkotsu.gef;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.draw2d.geometry.Rectangle;
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
	public NodeClass CreateBehavior(){
		
		NodeClass nClass = new NodeClass("NodeClass", new ConstructorCall("const"));
		
		NodeClass nClasss = new NodeClass("Albert", new ConstructorCall("const"));
		nClasss.setShape(new Rectangle(30,50,250,150));
		nClass.addSubClass(nClasss);
		
		return nClass;
		
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
		//viewer.setContents(composer.ClassView.getNodeClass());
		viewer.setContents(CreateBehavior());
	}


	@Override
	public void doSave(IProgressMonitor monitor) {
		// TODO Auto-generated method stub
		
	}
	

}
