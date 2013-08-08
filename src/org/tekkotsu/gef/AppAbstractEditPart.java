package org.tekkotsu.gef;

import java.beans.PropertyChangeListener;

import org.eclipse.gef.Request;
import org.eclipse.gef.RequestConstants;
import org.eclipse.gef.editparts.AbstractGraphicalEditPart;
import org.eclipse.ui.IPageLayout;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;

import org.tekkotsu.api.AbstractModel;

public abstract class AppAbstractEditPart extends AbstractGraphicalEditPart implements PropertyChangeListener {
	
	public void activate() {
		
		super.activate();
		
		((AbstractModel) getModel()).addPropertyChangeListener(this);
	}

	public void deactivate() {
		super.deactivate();
		((AbstractModel) getModel()).removePropertyChangeListener(this);
	}

	//Handles double click on graphical elements
	//Generates a request of REQ_OPEN handled in the perform request method
	@Override
	public void performRequest(Request req) {
		
		
		if (req.getType().equals(RequestConstants.REQ_OPEN)) {
			
			try {
				IWorkbenchPage page = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage();
				page.showView(IPageLayout.ID_PROP_SHEET);
			}
			catch (PartInitException e) {
                 e.printStackTrace();
            }
		 

		}
	}
}