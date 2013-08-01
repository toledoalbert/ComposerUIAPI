/*
 * Created on Oct 26, 2004
 */
package tekkotsu.edit.parts;

import java.beans.PropertyChangeEvent;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.draw2d.AbsoluteBendpoint;
import org.eclipse.draw2d.BendpointConnectionRouter;
import org.eclipse.draw2d.ColorConstants;
import org.eclipse.draw2d.Connection;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.PolygonDecoration;
import org.eclipse.draw2d.PolylineConnection;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.gef.EditPolicy;
import org.eclipse.swt.graphics.Color;
import org.eclipse.ui.views.properties.IPropertyDescriptor;
import org.eclipse.ui.views.properties.IPropertySource;
import org.tekkotsu.api.NodeInstance;
import org.tekkotsu.ui.util.AbstractModel;

import tgef.Node;
import tgef.NodeInstanceFigure;
import tgef.ui.editor.model.SubConnectionModel;
import editpolicies.AppRenamePolicy;
import editpolicies.MyBendpointEditPolicy;
import editpolicies.MyConnectionEditPolicy;


/**
 * @author asangpet
 *
 */
public class SubConnectionEditPart extends MyAbstractConnectionEditPart  {
	private PolylineConnection connection; // transition figure
	private PolygonDecoration arrowHead;
	private Color color = null;
	
	

	
	
	@Override
	protected void createEditPolicies() {
		//Allows deletion of transitions
		installEditPolicy(EditPolicy.CONNECTION_ROLE,
				new MyConnectionEditPolicy());
		
		//Allows changing of the spline curve points on
		// a transition
		installEditPolicy(EditPolicy.CONNECTION_BENDPOINTS_ROLE,
				new MyBendpointEditPolicy());
		
		installEditPolicy(EditPolicy.NODE_ROLE, new AppRenamePolicy());
		
	}
	
	@Override
	protected IFigure createFigure() {		
		SubConnectionModel model = (SubConnectionModel)this.getModel();
		connection = new PolylineConnection();
		connection.setConnectionRouter(new BendpointConnectionRouter());
		connection.setLineWidth(model.getLineWidth());
		
		arrowHead = new PolygonDecoration();		
		connection.setTargetDecoration(arrowHead);
		color = model.getColor();
		
		if (color==null) {
			if (((SubConnectionModel)getModel()).isPreview()) {
				connection.setForegroundColor(ColorConstants.lightGray);
			} else {
				connection.setForegroundColor(ColorConstants.black);
			}
		} else connection.setForegroundColor(color);
		
		return connection;
	}
	
	/* (non-Javadoc)
	 * @see java.beans.PropertyChangeListener#propertyChange(java.beans.PropertyChangeEvent)
	 */
	@Override
	public void propertyChange(PropertyChangeEvent evt) {		
		if (evt.getPropertyName().equals(SubConnectionModel.P_ARROWHEAD)) {
			Object newVal = evt.getNewValue();
			if (newVal.equals(SubConnectionModel.ARROW_NOHEAD)) {
				connection.setTargetDecoration(null);
			} else if (newVal.equals(SubConnectionModel.ARROW_ARROWHEAD)){
				arrowHead = new PolygonDecoration();
				connection.setTargetDecoration(arrowHead);
			}
		} else if (evt.getPropertyName().equals(SubConnectionModel.P_LINEWIDTH)) {
			SubConnectionModel model = (SubConnectionModel)getModel();
			connection.setLineWidth(model.getLineWidth());
		} else if (evt.getPropertyName().equals(Node.P_COLOR)) {
			color = (Color)evt.getNewValue();
			connection.setForegroundColor(color);
		} else {
			super.propertyChange(evt);
		}
		
		if (evt.getPropertyName().equals(SubConnectionModel.PROPERTY_COLOR)) refreshVisuals();
		if (evt.getPropertyName().equals(AbstractModel.PROPERTY_RENAME)) refreshVisuals();
		
		
	}
	
/*
	public Object getEditableValue() {
		return ((SubConnectionModel)getModel()).getParent2().getEditableValue();
	}
	
	public IPropertyDescriptor[] getPropertyDescriptors() {
		return ((SubConnectionModel)getModel()).getParent2().getPropertyDescriptors();		
	}
	
	public Object getPropertyValue(Object id) {
		return ((SubConnectionModel)getModel()).getParent2().getPropertyValue(id);
	}
	
	public boolean isPropertySet(Object id) {
		return ((SubConnectionModel)getModel()).getParent2().isPropertySet(id);		
	}
	
	public void resetPropertyValue(Object id) {
		((SubConnectionModel)getModel()).getParent2().resetPropertyValue(id);
	}
	public void setPropertyValue(Object id, Object value) {
		((SubConnectionModel)getModel()).getParent2().setPropertyValue(id,value);
	}
	*/
	
		
	
}
