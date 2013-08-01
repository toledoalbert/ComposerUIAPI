/*
 * Created on Oct 11, 2004
 */
package tgef;

import org.eclipse.gef.requests.CreationFactory;
//import org.jdom.Element;


import tgef.ui.editor.model.ModelData;

/**
 * @author asangpet
 */
public class SourceNodeModel extends SourceObjectModel {
	ModelData parent;
	
	//Element Xml was a parameter
	public SourceNodeModel(ModelData parent) {
		//String idTag = xml.getAttributeValue(IDTag.XML_state_id); 
		//if (!((idTag == null) || (idTag.equals("")))) setId(idTag);			
		//setClassName(xml.getAttributeValue(IDTag.XML_state_class));
		this.parent = parent;
	}
		
	public CreationFactory getFactory() {
		return new SourceNodeCreationFactory(this);
	}
	
	/*public Element getXML() {
		Element content = new Element("state");
		content.setAttribute("id",this.getId());
		content.setAttribute("class",this.getClassName());
		return content;
	}*/
	
	public ModelData getParentModel() {
		return parent;
	}
}

class SourceNodeCreationFactory implements CreationFactory {
	private SourceNodeModel model;
	
	public SourceNodeCreationFactory(SourceNodeModel model) {
		this.model = model;
	}
	public Object getNewObject() {
		return new Node(model);
	}
	public Object getObjectType() {
		return Node.class;
	}
}
