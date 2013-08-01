/*
 * Created on Sep 12, 2004
 */
package tekkotsu.edit.parts;

/**
 * @author asangpet
 */

import java.beans.PropertyChangeEvent;
import java.util.List;

import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.Layer;
import org.eclipse.draw2d.XYLayout;
import org.eclipse.gef.EditPolicy;
import org.tekkotsu.ui.util.AbstractModel;

import editpolicies.MyXYLayoutEditPolicy;
import tgef.*;

public class ViewEditPart extends MyAbstractGraphicalEditPart {
	@Override
	protected IFigure createFigure() {
		Layer figure = new Layer();
		figure.setLayoutManager(new XYLayout());
		return figure;
	}
	
	@Override
	protected List<Node> getModelChildren() {
		return ((LayoutData)getModel()).getStateChildren();
	}

	@Override
	protected void createEditPolicies() {
		installEditPolicy(EditPolicy.LAYOUT_ROLE, new MyXYLayoutEditPolicy());
	}
	
	public void propertyChange(PropertyChangeEvent evt) {
		if (evt.getPropertyName().equals(LayoutData.P_CHILDREN))
			refreshChildren();
	}	
}
