/*
 * Created on Oct 12, 2004
 */
package tekkotsu.edit.parts;

import java.beans.PropertyChangeEvent;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.eclipse.draw2d.ColorConstants;
import org.eclipse.draw2d.ConnectionAnchor;
import org.eclipse.draw2d.Ellipse;
import org.eclipse.draw2d.EllipseAnchor;
import org.eclipse.draw2d.Figure;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.Label;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gef.ConnectionEditPart;
import org.eclipse.gef.GraphicalEditPart;
import org.eclipse.gef.Request;
import org.tekkotsu.api.TransitionInstance;

import tgef.ui.editor.model.*;

import tgef.*;
/**
 * @author asangpet
 */
public class MultiTransitionEditPart extends NodeInstancePart {		
	@Override
	public ConnectionAnchor getSourceConnectionAnchor(ConnectionEditPart connection) {
		return new EllipseAnchor(shape);
	}
	
	@Override
	public ConnectionAnchor getTargetConnectionAnchor(ConnectionEditPart connection) {
		return new EllipseAnchor(shape);
	}
	
	@Override
	public ConnectionAnchor getSourceConnectionAnchor(Request request) {
		return new EllipseAnchor(shape);
	}
	
	@Override
	public ConnectionAnchor getTargetConnectionAnchor(Request request) {
		return new EllipseAnchor(shape);
	}

	@SuppressWarnings("unchecked")
	private Map<AbstractModel,NodeInstancePart> getModelToEditPart() {
		ViewEditPart viewer = (ViewEditPart)this.getParent();		
		Map<AbstractModel,NodeInstancePart> mapModelToEditPart = new HashMap<AbstractModel,NodeInstancePart>();
		List<NodeInstancePart> parts = viewer.getChildren();
		for (int i = 0; i < parts.size(); i++) {
			NodeInstancePart editPart = parts.get(i);
			mapModelToEditPart.put((AbstractModel)editPart.getModel(), editPart);
		}		
		return mapModelToEditPart;
	}
	
	/**
	 * Return default label location for the transition (midpoint)
	 * @return reference point for default label location
	 */
	private Point getDefaultLocation() {
		LayoutData view = (LayoutData)(((MultiTransitionModel)getModel()).getParent2());
		SourceTransitionModel srcModel = (SourceTransitionModel)((MultiTransitionModel)this.getModel()).getSource();
		
		Node source = (Node)view.getPartChild(srcModel.getSourceNodes().get(0));
		Node target = (Node)view.getPartChild(srcModel.getDestNodes().get(0));
		
		Map<AbstractModel, NodeInstancePart> model2part = getModelToEditPart();
		NodeInstancePart srcState = model2part.get(source);
		NodeInstancePart dstState = model2part.get(target);
				
		Point srcPoint = srcState.getSourceConnectionAnchor((ConnectionEditPart)null).getLocation(new Point(0,0));
		Point destPoint = dstState.getSourceConnectionAnchor((ConnectionEditPart)null).getLocation(new Point(0,0));

		int dx = destPoint.x-srcPoint.x;
		int dy = destPoint.y-srcPoint.y;
		float ds = (int)Math.sqrt(dx*dx+dy*dy);
		Point mid = new Point((srcPoint.x+destPoint.x)/2,(srcPoint.y+destPoint.y)/2);
		int du = 20;
		return mid.translate(Math.round(-dy*du/ds),Math.round(dx*du/ds));
	}
	
	private Rectangle getDefaultBound() {
		Point p = getDefaultLocation();		
		return new Rectangle(p.x,p.y,15,15);
	}
	
	@Override
	protected IFigure createFigure() {
		MultiTransitionModel model = (MultiTransitionModel)getModel();		
		holder = new Figure();		
		shape = new Ellipse();
		label = new Label();	

		if (model.isSingleTransition()) {
			org.eclipse.swt.graphics.Rectangle dummy = getViewer().getControl().getBounds();
			holder.setBounds(new Rectangle(dummy.width+1, dummy.height+1,1,1));
			return holder;
			//SubConnectionModel selector = (SubConnectionModel)model.getSubConnectionList().get(0);
			//Debugger.printDebug(Debugger.DEBUG_ALL,"Accessing "+this.getParent().getChildren()+" from "+model.getSubConnectionList());
		}
		
		if (model.getConstraint()==null) model.setConstraint(getDefaultBound());
		
		if (((LayoutData)(getParent().getModel())).isPreview()) {
			//shape.setBackgroundColor(model.getPreviewColor());
			shape.setBounds(model.getConstraint());	
			shape.setForegroundColor(ColorConstants.lightGray);
			label.setForegroundColor(ColorConstants.lightGray);
		} else {
			shape.setBackgroundColor(model.getColor());
			shape.setBounds(model.getConstraint());			
			shape.setForegroundColor(ColorConstants.black);
			label.setForegroundColor(ColorConstants.black);
		}
		
		//label.setText(model.getLabel());
		//label.setTextAlignment(PositionConstants.RIGHT);
		//label.setLabelAlignment(PositionConstants.RIGHT);
		holder.add(shape);
		//holder.add(label);
		holder.setBounds(model.getConstraint());
		return holder;				
	}
	
	@Override
	public void propertyChange(PropertyChangeEvent evt) {
		if (evt.getPropertyName().equals(MultiTransitionModel.P_LINEWIDTH)) {
			Iterator<TransitionInstance> iter = ((MultiTransitionModel)getModel()).getSubConnectionList().iterator();
			while (iter.hasNext()) {
				TransitionInstance model = iter.next();
				model.setLineWidth(((MultiTransitionModel)getModel()).getLineWidth());
				//setDirty();
			}
		} else super.propertyChange(evt); 
	}	
	
	/**
	 * Adjust label color according to background color of the figure
	 */
	@Override
	protected void setLabelColor() {
		if (((MultiTransitionModel)getModel()).isPreview()) {
			label.setForegroundColor(ColorConstants.lightGray);
		} else {
			label.setForegroundColor(ColorConstants.black);
		}
	}
	
	protected Rectangle getShapeConstraint(Rectangle constraint) {
		Rectangle shapeConstraint = new Rectangle(constraint);
		shapeConstraint.setSize(shapeConstraint.height>>1, shapeConstraint.height>>1);
		shapeConstraint.setLocation(shapeConstraint.getLeft());
		return shapeConstraint;
	}
	
	@Override
	protected void refreshVisuals() {
		MultiTransitionModel model = (MultiTransitionModel)getModel();
		Rectangle constraint = model.getConstraint();
		
		if( constraint == null )
		{
			constraint = getDefaultBound();
		}
		
		if (!model.isSingleTransition()) {
			((GraphicalEditPart)getParent()).setLayoutConstraint(this, getFigure(), constraint);
			holder.setBounds(constraint);
			shape.setBounds(getShapeConstraint(constraint));
			label.setBounds(constraint);
		} else {
			constraint.setLocation(getDefaultLocation());
		}
	}
}
