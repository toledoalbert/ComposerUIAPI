package part.tree;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import tgef.Node;

import org.eclipse.gef.DragTracker;
import org.eclipse.gef.Request;
import org.eclipse.gef.RequestConstants;
import org.eclipse.gef.editparts.AbstractTreeEditPart;
import org.eclipse.gef.tools.SelectEditPartTracker;
import org.eclipse.ui.IPageLayout;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;

//Edit part for tree outline view added to prespective.java
public class AppAbstractTreeEditPart extends AbstractTreeEditPart implements PropertyChangeListener {

	@Override
	public void propertyChange(PropertyChangeEvent evt) {
		// TODO Auto-generated method stub
		
	}
	
	public void activate() {
		super.activate();
		((Node) getModel()).addPropertyChangeListener(this);
		}

	public void deactivate() {
		((Node) getModel()).removePropertyChangeListener(this);
		super.deactivate();
		}
	
	//handles double clicking for properties sheet in the tree
	@Override
	public DragTracker getDragTracker(Request req) {
	return new SelectEditPartTracker(this);
	}
	
	@Override
	public void performRequest(Request req) {
	if (req.getType().equals(RequestConstants.REQ_OPEN)) {
	try {
	IWorkbenchPage page =
	PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage();
	page.showView(IPageLayout.ID_PROP_SHEET);
	}
	catch (PartInitException e) {
	e.printStackTrace();
	}
	}
	}



}
