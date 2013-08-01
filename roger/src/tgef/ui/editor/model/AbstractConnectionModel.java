/*
 * Created on Sep 18, 2004
 */
package tgef.ui.editor.model;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.draw2d.geometry.Point;

import tgef.Node;
import tgef.SourceObjectModel;
import java.beans.PropertyChangeSupport;
import java.beans.PropertyChangeListener;
import org.eclipse.ui.views.properties.IPropertySource;
import org.eclipse.core.runtime.IAdaptable;
import org.tekkotsu.api.TransitionClass;
import org.tekkotsu.ui.util.AbstractModel;

public abstract class AbstractConnectionModel extends AbstractModel{
	private Node source, target;
private TransitionClass type;
	private List<Point> bendpoints   = new ArrayList<Point>();

	public static final String P_BEND_POINT = "_bend_point";

	public AbstractConnectionModel(List<Point> bendpoints) {
		if( bendpoints != null ) {
			for(int i = 0; i < bendpoints.size(); ++i) {
				Point point = bendpoints.get(i);
				if( point.y < 4 ) point.y = 4;
				if( point.x < 4 ) point.x = 4;
				this.bendpoints.add(bendpoints.get(i));
			}
		}
	}
	
	public AbstractConnectionModel(Node source, Node target){
		this.source=source;
		this.target=target;
	}
	
	public AbstractConnectionModel(TransitionClass type) {
		this.type=type;
	}

	public List<Point> getBendpoints() {
		return bendpoints;
	}
	
	public void addBendpoint(int index, Point point) {
		if( point.y < 4 ) point.y = 4;
		if( point.x < 4 ) point.x = 4;
 		bendpoints.add(index, point);
		getListeners().firePropertyChange(P_BEND_POINT, null, null);
	}

	public void removeBendpoint(int index) {
		bendpoints.remove(index);
		getListeners().firePropertyChange(P_BEND_POINT, null, null);
	}

	public void replaceBendpoint(int index, Point point) {
		if( point.y < 4 ) point.y = 4;
		if( point.x < 4 ) point.x = 4;
		bendpoints.set(index, point);
		getListeners().firePropertyChange(P_BEND_POINT, null, null);
	}

	/*@Override
	public boolean isPreview() {
		AbstractModel parent = getParent();
		
		return parent == null ? false : parent.isPreview();
	}*/

	//@Override
	public void attachSource() {
		if (!source.getModelSourceConnections().contains(this))
			source.addSourceConnection(this);
	}

	public void attachTarget() {
		if (!target.getModelTargetConnections().contains(this))
			target.addTargetConnection(this);
	}

	public void detachSource() {
		source.removeSourceConnection(this);
	}

	public void detachTarget() {
		target.removeTargetConnection(this);
	}

	public Node getSource() {
		return source;
	}

	public Node getTarget() {
		return target;
	}

	public void setSource(Node model) {
		source = model;
	}

	public void setTarget(Node model) {
		target = model;
	}
	
	public String partialToString( Node snm ) {
		return toString();
	}

	public void clearBendpoints() {
		bendpoints.clear();
		getListeners().firePropertyChange(P_BEND_POINT, null, null);
	}
}
