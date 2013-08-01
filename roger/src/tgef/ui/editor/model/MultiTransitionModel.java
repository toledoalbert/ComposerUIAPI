/*
 * Created on Oct 12, 2004
 */
package tgef.ui.editor.model;

//import static org.tekkotsu.ui.editor.resources.IDTag.*;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.Rectangle;
import tgef.LayoutData;

import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.RGB;
import org.eclipse.ui.views.properties.ColorPropertyDescriptor;
import org.eclipse.ui.views.properties.IPropertyDescriptor;
import org.eclipse.ui.views.properties.PropertyDescriptor;
import org.eclipse.ui.views.properties.TextPropertyDescriptor;
import org.tekkotsu.api.TransitionInstance;
import org.tekkotsu.ui.util.Debugger;
import org.tekkotsu.ui.util.Geometry;
//import org.jdom.Content;
//import org.jdom.Element;



import tgef.Node;
import tgef.SourceTransitionModel;


/**
 * Multi-transition model, which actually looks like a node.
 * 
 * @author asangpet
 */
public class MultiTransitionModel extends Node {
	public static final String P_LINEWIDTH = "_linewidth";

	protected int lineWidth = 1;

	private List<TransitionInstance> subConnectionList = new ArrayList<TransitionInstance>();

	public MultiTransitionModel() {
		super();
		//Debugger.printDebug(Debugger.DEBUG_ALL,
		// "execute MultiTransitionModel()");
		//setId("transition" + Integer.toString(nextStateID++));
		setLabel(getName());
		//setShape(StateNodeModel.P_SHAPE_STYLE_ELLIPSE);
		//setColor(new RGB(0, 0, 0));
	}

	public void setLabel(String name) {
		// TODO Auto-generated method stub
		
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.tekkotsu.ui.editor.model.StateNodeModel#setColor(org.eclipse.swt.
	 * graphics.RGB)
	 */
	/*@Override
	public void setColor(RGB color) {
		super.setColor(color);
		Iterator<SubConnectionModel> iter = subConnectionList.iterator();
		while (iter.hasNext()) {
			SubConnectionModel sub = iter.next();
			sub.setColor(getColor());
		}
	}*/

/*	public MultiTransitionModel(Element xml, LayoutData parent) {
		this();
		setParent(parent);
		assert (XML_transition_tag.equals(xml.getName()));
		parseXML(xml);
	}*/

	/**
	 * A constructor which were used when user add transition to the layout
	 * 
	 * @param source
	 *            Model source for this transition
	 * @param parent
	 *            View model which will contain this transition
	 */
	public MultiTransitionModel(SourceTransitionModel source, LayoutData parent) {
		this();
		Debugger.printDebug(Debugger.DEBUG_ALL, "execute MultiTransitionModel("
				+ source + "," + parent + ")");
		initModel(source);
	}

	/**
	 * Perform initial action after created the transition node. Attach all
	 * available connection from source to transition node and from transition
	 * node to destination
	 */
	@Override
	public void doPostAdd() {
		doPreDelete((LayoutData) (this.getParent2()));
		/* Connect associate transition to multitransition node */
		SourceTransitionModel source = (SourceTransitionModel) getSource();
		LayoutData parent = (LayoutData)getParent2();
		subConnectionList = new ArrayList<TransitionInstance>();

		/*
		 * Single Transition Case
		 */
		if ((source.getSourceNodes().size() == 1)
				&& (source.getDestNodes().size() == 1)) {
			String sourceNodeId = source.getSourceNodes().get(0);
			String destNodeId = source.getDestNodes().get(0);
			Node sourceNode = (Node) parent
					.getPartChild(sourceNodeId);
			Node destNode = (Node) parent
					.getPartChild(destNodeId);
			TransitionInstance connx = new TransitionInstance(this, sourceNode,
					destNode, parent.getConnectionDescriptor(sourceNodeId,
							destNodeId), lineWidth);
			
			if( connx.getBendpoints().isEmpty() )
			{
				Point sourceCenter = Geometry.center(sourceNode.getConstraint());
				Point targetCenter = Geometry.center(destNode.getConstraint());
				Point center       = Geometry.center(sourceCenter,targetCenter);
				
				double xoff = 0;
				double yoff = 0;
				
				//bias the connection based on the source
				if( center.x > sourceCenter.x )
				{
					xoff -= 15;
					yoff -= 15;
				}
				else
				{
					xoff += 15;
					yoff += 15;
				}
				
				double xc = center.x + xoff;
				double yc = center.y + yoff;
				connx.addBendpoint(0, new Point(xc,yc));
			}
			
			subConnectionList.add(connx);
			/*if (!isPreview())
				connx.setColor(this.getColor());
			else
				connx.setColor(this.getPreviewColor());*/
			return;
		}

		/*
		 * Multi-transition case
		 */

		// generate src/target set.
		// connect source node
		for (String srcId : source.getSourceNodes()) {
			Node sourceNode = (Node) parent
					.getPartChild(srcId);
			if (sourceNode != null) {
				TransitionInstance inConnx = new TransitionInstance(this,
						sourceNode, this, parent.getConnectionDescriptor(
								sourceNode.getId(), this.getId()),
						SubConnectionModel.ARROW_NOHEAD, lineWidth);
				subConnectionList.add(inConnx);
				// parent.addChild(inConnx);
				/*if (!isPreview())
					inConnx.setColor(this.getColor());
				else
					inConnx.setColor(this.getPreviewColor());*/
			}
		}

		// connect destination node
		for (String destId : source.getDestNodes()) {
			Node destNode = (Node) parent
					.getPartChild(destId);
			if (destNode != null) {
				TransitionInstance outConnx = new TransitionInstance(this,
						this, destNode, parent.getConnectionDescriptor(this
								.getId(), destNode.getId()), lineWidth);
				subConnectionList.add(outConnx);
				// parent.addChild(outConnx);
				/*if (!isPreview())
					outConnx.setColor(this.getColor());
				else
					outConnx.setColor(this.getPreviewColor());*/
			}
		}
	}

	/**
	 * Perform finalize action before removal of transition node. (Detach all
	 * available connections from the content)
	 * 
	 * @param contents
	 */
	@Override
	public void doPreDelete(LayoutData contents) {
		List<TransitionInstance> subConn = new ArrayList<TransitionInstance>(
				subConnectionList);
		for (TransitionInstance model : subConn) {
			model.detachSource();
			model.detachTarget();
			contents.removeChild(model);
		}
	}

	public void removeSubConnection(TransitionInstance sub) {
		sub.detachSource();
		sub.detachTarget();
	}

	/**
	 * @return Returns the lineWidth.
	 */
	public int getLineWidth() {
		return lineWidth;
	}

	/*@Override
	public IPropertyDescriptor[] getPropertyDescriptors() {
		PropertyDescriptor idProp = new PropertyDescriptor(P_ID, "ID");
		PropertyDescriptor classProp = new PropertyDescriptor(P_CLASS, "Class");
		PropertyDescriptor sourceProp = new PropertyDescriptor(P_SOURCE_CONNECTION, "Sources");
		PropertyDescriptor targetProp = new PropertyDescriptor(P_TARGET_CONNECTION, "Targets");
		
		PropertyDescriptor colorProp = new ColorPropertyDescriptor(P_COLOR,
				"Color");
		TextPropertyDescriptor lineProp = new TextPropertyDescriptor(
				P_LINEWIDTH, "Line width");

		idProp.setCategory(PROPERTIES_MODEL_CATEGORY);
		classProp.setCategory(PROPERTIES_MODEL_CATEGORY);
		sourceProp.setCategory(PROPERTIES_MODEL_CATEGORY);
		targetProp.setCategory(PROPERTIES_MODEL_CATEGORY);
		colorProp.setCategory(PROPERTIES_APPEARANCE_CATEGORY);
		lineProp.setCategory(PROPERTIES_APPEARANCE_CATEGORY);

		return new IPropertyDescriptor[] { idProp, classProp, sourceProp, targetProp,
				colorProp, lineProp };
	}*/

	/*@Override
	public Object getPropertyValue(Object id) {
		if (id.equals(P_LINEWIDTH)) {
			return String.valueOf(getLineWidth());
		} else if ( id.equals(P_SOURCE_CONNECTION) ) {
			return getSourcesString();
		} else if ( id.equals(P_TARGET_CONNECTION) ) {
			return getDestsString();
		}

		return super.getPropertyValue(id);
	}

	/**
	 * @return Returns the subConnectionList.
	 */
	public List<TransitionInstance> getSubConnectionList() {
		return subConnectionList;
	}

	/*@Override
	public void parseXML(Element xml)
	{
		try {
			super.parseXML(xml);
			setId(xml.getAttributeValue(XML_transition_id));
			setLabel(xml.getAttributeValue(XML_common_label));
			setLineWidth(Integer.parseInt(xml
					.getAttributeValue(XML_transition_linewidth)));

			RGB color = tag2RGB(xml.getAttributeValue(XML_common_color));
			setColor(color);

			SourceTransitionModel model = parent.getModelData()
					.getTransition(this.getId());
			initModel(model);

			@SuppressWarnings("unchecked")
			List<Element> children = xml.getChildren();
			for (Element child : children) {
				if (XML_transition_bendpoints.equals(child.getName())) {
					String source = child
							.getAttributeValue(XML_transition_source);
					String dest = child.getAttributeValue(XML_transition_dest);

					List<Point> bendpoints = new ArrayList<Point>();

					@SuppressWarnings("unchecked")
					List<Element> epoints = child.getChildren();
					for (Element ep : epoints) {
						if (XML_transition_bendpoint.equals(ep.getName())) {
							int x = Integer
									.parseInt(ep
											.getAttributeValue(XML_transition_bendpoint_x));
							int y = Integer
									.parseInt(ep
											.getAttributeValue(XML_transition_bendpoint_y));
							if( y < 4 ) y = 4;
							if( x < 4 ) x = 4;
							bendpoints.add(new Point(x, y));
						}
					}

					parent.putConnectionDescriptor(source, dest, bendpoints);
				}
			}
		} catch (Exception e) {
			Debugger.printThrowable(e);
		}
	}
	
	@Override
	public Content getXML() {
		Element content = new Element(XML_transition_tag);
		content.setAttribute(XML_state_id, getId());
		if (getSource().getClassName() != null)
			content.setAttribute(XML_state_class, getSource().getClassName());
		content.setAttribute(XML_state_color, RGB2Tag(getRGB()));
		content.setAttribute(XML_transition_linewidth, "" + lineWidth);

		for (SubConnectionModel sub : getSubConnectionList()) {
			List<Point> points = sub.getBendpoints();
			if (points.size() > 0) {
				Element xmlPoints = new Element(XML_transition_bendpoints);
				xmlPoints.setAttribute(XML_transition_source, sub.getSource()
						.getId());
				xmlPoints.setAttribute(XML_transition_dest, sub.getTarget()
						.getId());

				for (Point p : sub.getBendpoints()) {
					Element xmlPoint = new Element(XML_transition_bendpoint);
					xmlPoint.setAttribute(XML_transition_bendpoint_x, "" + p.x);
					xmlPoint.setAttribute(XML_transition_bendpoint_y, "" + p.y);
					xmlPoints.addContent(xmlPoint);
				}

				content.addContent(xmlPoints);
			}
		}

		if (!isSingleTransition()) {
			Rectangle rect = getConstraint();
			content.setAttribute(XML_state_top, "" + rect.y);
			content.setAttribute(XML_state_left, "" + rect.x);
			content.setAttribute(XML_state_width, "" + rect.width);
			content.setAttribute(XML_state_height, "" + rect.height);
			content.setAttribute(XML_state_shape, getShape());
		}

		return content;
	}
*/
	public void initModel(SourceTransitionModel source) {
		//setShape(StateNodeModel.P_SHAPE_STYLE_ELLIPSE);
		setId(source.getId());
		setLabel("");

		super.setSource(source);
	}

	/*@Override
	public boolean isPropertySet(Object id) {
		return id.equals(P_LINEWIDTH) || super.isPropertySet(id);
	}

	/**
	 * @param lineWidth
	 *            The lineWidth to set.
	 */
	public void setLineWidth(int lineWidth) {
		this.lineWidth = lineWidth;
		getListeners().firePropertyChange(P_LINEWIDTH, null, Integer.valueOf(lineWidth));
	}

	/*@Override
	public void setPropertyValue(Object id, Object value) {
		if (id.equals(P_LINEWIDTH)) {
			setLineWidth(Integer.parseInt(value.toString()));
		} else
			super.setPropertyValue(id, value);
	}*/

	public boolean isSingleTransition() {
		SourceTransitionModel source = (SourceTransitionModel) getSource();
		return source.getSourceNodes().size() == 1
				&& source.getDestNodes().size() == 1;
	}

	//Color related
	/*@Override
	public void activate() {
		super.activate();
		for (SubConnectionModel sub : subConnectionList) {
			sub.props.firePropertyChange(P_COLOR, null, this.getColor());
		}
	}

	@Override
	public void deactivate() {
		super.deactivate();
		for (SubConnectionModel sub : subConnectionList) {
			sub.props.firePropertyChange(P_COLOR, null, this.getPreviewColor());
		}
	}*/
	
	public String getSourcesString() {
		List< TransitionInstance > sources = new ArrayList<TransitionInstance>();
		String s = "";
		
		if(subConnectionList.size() == 1) {
			return subConnectionList.get(0).getSource().toString();
		}
		for( TransitionInstance sub : subConnectionList ) {
			if( sub.getTarget() instanceof MultiTransitionModel ) {
				sources.add(sub);
			}
		}
		
		if( sources.size() > 1 ) {
			s += "{";
		}
		
		for(int i = 0; i < sources.size(); ++i) {
			s += sources.get(i).getSource().toString() + ( i == sources.size()-1 ? "" : ",");
		}
		
		
		if( sources.size() > 1 ) {
			s += "}";
		}
		
		return s;
	}
	
	public String getDestsString() {
		List< TransitionInstance > dests = new ArrayList<TransitionInstance>();
		String s = "";
		
		if(subConnectionList.size() == 1) {
			return subConnectionList.get(0).getTarget().toString();
		}
		for( TransitionInstance sub : subConnectionList ) {
			if( sub.getSource() instanceof MultiTransitionModel ) {
				dests.add(sub);
			}
		}
		
		if( dests.size() > 1 ) {
			s += "{";
		}
		
		for(int i = 0; i < dests.size(); ++i) {
			s += dests.get(i).getTarget().toString() + ( i == dests.size()-1 ? "" : ",");
		}
		
		
		if( dests.size() > 1 ) {
			s += "}";
		}
		
		return s;
	}

	public Color getColor() {
		// TODO Auto-generated method stub
		return null;
	}
}
