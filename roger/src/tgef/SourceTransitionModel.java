/*
 * Created on Oct 11, 2004
 */
package tgef;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.gef.requests.CreationFactory;
import tgef.ui.editor.model.MultiTransitionModel;

/**
 * This is the source model for associated MultiTransitionModel
 * @author asangpet
 */
public class SourceTransitionModel extends SourceObjectModel {
	String type;
	List<String> sourceNodes = new ArrayList<String>();
	List<String> destNodes   = new ArrayList<String>();
	
	/*@SuppressWarnings("unchecked")
	public SourceTransitionModel(Element xml) {
		if (IDTag.XML_transition_tag.equals(xml.getName())) {
			try {
				setId(xml.getAttributeValue(IDTag.XML_transition_id));
				setType(xml.getAttributeValue(IDTag.XML_transition_type));
				setClassName(xml.getAttributeValue(IDTag.XML_transition_class));
				
				// iterate through children to get source/dest nodes
				Iterator<Object> iter = xml.getChildren().iterator();
				while (iter.hasNext()) {
					Element child = (Element)(iter.next());
					if (child.getName().equals(IDTag.XML_transition_source)) {
						String t = child.getAttributeValue(IDTag.XML_common_id);
						if( t == null )
						{
							t = child.getText();
						}
						sourceNodes.add(t);
					} else if (child.getName().equals(IDTag.XML_transition_dest))
					{
						String t = child.getAttributeValue(IDTag.XML_common_id);
						if( t == null )
						{
							t = child.getText();
						}
						destNodes.add(t);
					}
				}
			} catch (Exception e) {
				Debugger.printThrowable(e);
			}
		}	
	}
	
	/**
	 * @return true if the transition is multi-transition, otherwise, return false
	 */
	public boolean isMultiTransition() {
		return (sourceNodes.size()>1) || (destNodes.size()>1);
	}

	/**
	 * @return Returns the destNodes.
	 */
	public List<String> getDestNodes() {
		return destNodes;
	}
	/**
	 * @return Returns the sourceNodes.
	 */
	public List<String> getSourceNodes() {
		return sourceNodes;
	}

	/**
	 * @return Returns the type.
	 */
	public String getType() {
		return type;
	}
	
	/**
	 * @param type The type to set.
	 */
	public void setType(String type) {
		this.type = type;
	}
	
	public CreationFactory getFactory(LayoutData viewmodel) {
		return new SourceTransitionCreationFactory(this, viewmodel);
	}

	/*public Element getXML() {
		Element content = new Element(IDTag.XML_transition_tag);
		content.setAttribute(IDTag.XML_common_id,this.getId());
		content.setAttribute(IDTag.XML_common_class,this.getClassName());
		
		Iterator<String> iter = getSourceNodes().iterator();
		while (iter.hasNext()) {
			Element node = new Element(IDTag.XML_transition_source);
			String t = iter.next();
			node.setAttribute(IDTag.XML_common_id,t);
			content.addContent(node);
		}
		iter = getDestNodes().iterator();
		while (iter.hasNext()) {
			Element node = new Element(IDTag.XML_transition_dest);
			node.setAttribute(IDTag.XML_common_id,iter.next());	
			content.addContent(node);
		}
		
		return content;
	}	
}*/


class SourceTransitionCreationFactory implements CreationFactory {
	private SourceTransitionModel model;
	private LayoutData viewModel;
	
	public SourceTransitionCreationFactory(SourceTransitionModel model, LayoutData viewModel) {
		this.model = model;
		this.viewModel = viewModel;
	}
	public Object getNewObject() {
		return new MultiTransitionModel(model, viewModel);
	}
	public Object getObjectType() {
		return MultiTransitionModel.class;
	}
}
}