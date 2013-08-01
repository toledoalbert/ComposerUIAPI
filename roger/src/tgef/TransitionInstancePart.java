package tgef;

import java.beans.PropertyChangeEvent;

import org.eclipse.draw2d.BendpointConnectionRouter;
import org.eclipse.draw2d.ColorConstants;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.Label;
import org.eclipse.draw2d.MidpointLocator;
import org.eclipse.draw2d.PolygonDecoration;
import org.eclipse.draw2d.PolylineConnection;
import org.eclipse.gef.EditPolicy;
import org.eclipse.gef.editpolicies.ConnectionEndpointEditPolicy;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Color;
import org.tekkotsu.api.TransitionInstance;

import tekkotsu.edit.parts.MyAbstractConnectionEditPart;
import tgef.ui.editor.model.SubConnectionModel;

import editpolicies.AppTransitionInstanceDeleteEditPolicy;
import editpolicies.MyBendpointEditPolicy;
import editpolicies.MyConnectionEditPolicy;

public class TransitionInstancePart extends MyAbstractConnectionEditPart{

		private PolylineConnection connection; // transition figure
		private PolygonDecoration arrowHead;
		private Color color = null;
		
		

		
		
		@Override
		protected void createEditPolicies() {
			
			
			//Allows changing of the spline curve points on
			// a transition
			installEditPolicy(EditPolicy.CONNECTION_BENDPOINTS_ROLE,
					new MyBendpointEditPolicy());
			
			installEditPolicy(EditPolicy.CONNECTION_ROLE, new MyConnectionEditPolicy());
			
			
		}
		
		@Override
		protected IFigure createFigure() {		
			TransitionInstance model = (TransitionInstance)this.getModel();
			connection = new PolylineConnection();
			connection.setConnectionRouter(new BendpointConnectionRouter());
			connection.setLineWidth(model.getLineWidth());
			
			arrowHead = new PolygonDecoration();		
			connection.setTargetDecoration(arrowHead);
			//color = model.getColor();
			
			if (color==null) {
				if (((TransitionInstance)getModel()).isPreview()) {
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
		}*/
		
		/*protected void refreshVisuals(){
			SubConnectionEditPart figure = (SubConnectionEditPart)getFigure();
			//SubConnectionModel model = (SubConnectionModel)getModel();
			
			List<Point> modelConstraint = ((SubConnectionModel)getModel()).getBendpoints();
		    List<AbsoluteBendpoint> figureConstraint = new ArrayList<AbsoluteBendpoint>();
		    for (Point p : modelConstraint) {
		      figureConstraint.add(new AbsoluteBendpoint(p));
		    }
		    ((Connection) figure).setRoutingConstraint(figureConstraint);
		}*/
			
	}
		
	
