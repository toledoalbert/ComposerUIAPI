/*
 * Created on Sep 18, 2004
 */
package tekkotsu.edit.parts;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.draw2d.AbsoluteBendpoint;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.PolygonDecoration;
import org.eclipse.draw2d.PolylineConnection;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.gef.editparts.AbstractConnectionEditPart;
import org.eclipse.ui.PlatformUI;
//import org.tekkotsu.ui.editor.StateMachineEditor;
import tgef.ui.editor.model.AbstractConnectionModel;

/**
 * @author asangpet
 */
public abstract class MyAbstractConnectionEditPart extends
		AbstractConnectionEditPart implements PropertyChangeListener {

	//Just added to draw polyline
	protected IFigure createFigure() {
	        PolylineConnection connection = (PolylineConnection) super.createFigure();
	        connection.setLineWidth(2);
	        PolygonDecoration decoration = new PolygonDecoration();
	        decoration.setTemplate(PolygonDecoration.TRIANGLE_TIP);
	        connection.setTargetDecoration(decoration);
	        return connection;
	}
		        
	        
	        
	        
	@Override
	public void activate() {
		super.activate();
		((AbstractConnectionModel) getModel()).props.addPropertyChangeListener(this);
	}

	@Override
	public void deactivate() {
		super.deactivate();
		((AbstractConnectionModel) getModel())
				.props.removePropertyChangeListener(this);
	}
	
	/*protected void setDirty() {
		StateMachineEditor editor = (StateMachineEditor)PlatformUI.getWorkbench()
		  .getActiveWorkbenchWindow().getActivePage().getActiveEditor();
		
		editor.setDirty();
	}*/

	public void propertyChange(PropertyChangeEvent evt) {
		if (evt.getPropertyName().equals(AbstractConnectionModel.P_BEND_POINT)) {
			refreshBendpoints();
			//setDirty();
		}
	}

	protected void refreshBendpoints() {
		List<Point> bendpoints = ((AbstractConnectionModel) getModel())
				.getBendpoints();
		List<Point> constraint = new ArrayList<Point>();
		
		for(Point p : bendpoints)
		{
			constraint.add(new AbsoluteBendpoint(p));
		}
		
		getConnectionFigure().setRoutingConstraint(constraint);
	}

	@Override
	protected void refreshVisuals() {
		refreshBendpoints();
	}
}
