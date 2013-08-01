/*
 * Created on Oct 26, 2004
 */
package tgef.ui.editor.model;

import java.util.List;

import org.eclipse.draw2d.geometry.Point;
import org.eclipse.swt.graphics.Color;
//import org.jdom.Content;
import tgef.Node;

/**
 * @author asangpet
 *
 */
public class SubConnectionModel extends AbstractConnectionModel {
	public static final String P_ARROWHEAD = "_arrowhead";
	public static final String P_LINEWIDTH = "_linewidth";
	public static final String ARROW_ARROWHEAD = "_arrow_head";
	public static final String ARROW_NOHEAD = "_arrow_nohead";	
	
	private String arrowType;
	private String name;
	private int lineWidth;
	private Color color;
	private MultiTransitionModel _parent;
	private Node sourceNode;
	private Node targetNode;
	
	public static final String PROPERTY_COLOR = "SubConnectionModelColor";
	
	public SubConnectionModel(MultiTransitionModel parent, Node source, Node dest, List<Point> bendpoints) {
		this(parent ,source, dest, bendpoints, ARROW_ARROWHEAD, 1);		
	}

	public SubConnectionModel(MultiTransitionModel parent, Node source, Node dest, List<Point> bendpoints, int lineWidth) {
		this(parent ,source, dest, bendpoints, ARROW_ARROWHEAD, lineWidth);		
	}

	public SubConnectionModel(Node Source, Node target){
		super(Source, target);
		
		
	}
	
	public SubConnectionModel(MultiTransitionModel parent, Node source, Node dest, List<Point> bendpoints, String arrowType, int lineWidth) {
		super(bendpoints);
		setParent(parent);
		this._parent = parent;
		this.arrowType = arrowType;
		this.lineWidth = lineWidth;
		setSource(source);
		setTarget(dest);
		attachSource();
		attachTarget();
	}
	
	/* (non-Javadoc)
	 * @see org.tekkotsu.ui.editor.model.AbstractModel#getXML()
	 */
	/*@Override
	public Content getXML() {
		return null;
	}*/

	/**
	 * @return Returns the arrowType.
	 */
	
	public String getName(){
		return name;
	}
	
	public void setName(String name){
		this.name=name;
	}
	public String getArrowType() {
		return arrowType;
	}
	/**
	 * @param arrowType The arrowType to set.
	 */
	public void setArrowType(String arrowType) {
		this.arrowType = arrowType;
		getListeners().firePropertyChange(P_ARROWHEAD, null, arrowType);
	}
	
	public void setLineWidth(int width) {
		lineWidth = width;
		getListeners().firePropertyChange(P_LINEWIDTH, null, Integer.valueOf(width));
	}
	
	public int getLineWidth() {
		return lineWidth;
	}
	
	@Override
	public String toString() {
		return _parent.getName();
	}
	
	@Override
	public String partialToString(Node snm) {	
		if( snm.equals( getSource() ) ) {
			//we are a single out trans
			return _parent.getName() + " : " + _parent.getDestsString();
		} else if ( snm.equals( getTarget() ) ) {
			//we are a single in trans
			return _parent.getSourcesString() + " : " + _parent.getName();
		} else {
			//failsafe
			return toString();
		}
	}

	@Override
	public boolean isPreview() {
		// TODO Auto-generated method stub
		return false;
	}

	
	public void setColor(Color color) {
		/*this.color = color;
		getListeners().firePropertyChange(Node.P_COLOR,null,color);*/
		Color oldColor = this.color;
		this.color = color;
		getListeners().firePropertyChange(PROPERTY_COLOR, oldColor, color);
	}

	public Color getColor() {
		return color;
	}

	@Override
	public Object getAdapter(Class adapter) {
		// TODO Auto-generated method stub
		return null;
	}
}
