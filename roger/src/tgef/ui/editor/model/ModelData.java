/*
 * Created on Oct 4, 2004
 */
package tgef.ui.editor.model;

import java.io.Reader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

//import org.jdom.Document;
//import org.jdom.Element;
//import org.jdom.input.SAXBuilder;
//import org.tekkotsu.ui.editor.resources.IDTag;
import org.tekkotsu.ui.util.Debugger;

import tgef.SourceNodeModel;
import tgef.SourceObjectModel;
import tgef.SourceTransitionModel;

/**
 * This class represents the organization of model file
 * 
 * @author asangpet
 */
public class ModelData {
	List<SourceNodeModel> nodes = new ArrayList<SourceNodeModel>();
	List<SourceTransitionModel> transitions = new ArrayList<SourceTransitionModel>();

	Map<String, SourceNodeModel> nodeMap = new HashMap<String, SourceNodeModel>();
	Map<String, SourceTransitionModel> transitionMap = new HashMap<String, SourceTransitionModel>();

	String machineName = null;

	public ModelData() {
		// nothing
	}

	/*public void parseXML(Element xml) {
		try {
			nodeMap = new HashMap<String, SourceNodeModel>();
			transitionMap = new HashMap<String, SourceTransitionModel>();
			nodes = new ArrayList<SourceNodeModel>();
			transitions = new ArrayList<SourceTransitionModel>();

			if ("model".equals(xml.getName())) {
				setMachineName(xml.getAttributeValue(IDTag.XML_common_id));
				_parseChildren(xml);
			}
		} catch (Exception e) {
			Debugger.printThrowable(e);
		}
	}*/

	/*
	public void _parseChildren(Element xml) {
		@SuppressWarnings("unchecked")
		List<Element> children = xml.getChildren();
		
		for (Element child : children) {
			if (IDTag.XML_state_tag.equals(child.getName())) {
				_parseState(child);
			} else if (IDTag.XML_transition_tag.equals(child.getName())) {
				_parseTransition(child);
			}
		}
	}*/
	
	/*public void _parseState(Element xml) {
		SourceNodeModel temp = new SourceNodeModel(xml, this);
		nodes.add(temp);
		nodeMap.put(temp.getId(), temp);
		_parseChildren(xml);
	}

	public void _parseTransition(Element xml) {
		SourceTransitionModel temp = new SourceTransitionModel(xml);
		transitions.add(temp);
		transitionMap.put(temp.getId(), temp);
	}*/

	public void setMachineName(String name) {
		machineName = name;
	}

	public String getMachineName() {
		return machineName;
	}

	public SourceNodeModel getNode(String id) {
		return nodeMap.get(id);
	}

	public SourceTransitionModel getTransition(String id) {
		return transitionMap.get(id);
	}

	public SourceObjectModel getChild(String id) {
		SourceObjectModel result = nodeMap.get(id);

		if (nodeMap.get(id) != null)
			return result;

		return transitionMap.get(id);
	}

	public List<SourceNodeModel> getNodes() {
		return nodes;
	}

	public List<SourceTransitionModel> getTransitions() {
		return transitions;
	}

	/*public void setSourceInput(Reader reader) {
		SAXBuilder builder = new SAXBuilder();
		try {
			Document sourcedoc = builder.build(reader);
			parseXML(sourcedoc.getRootElement());
		} catch (Exception e) {
			// error reading input file
			Debugger.printThrowable(e);
		}
	}*/

	/*public Element getXML() {
		Element content = new Element("model");

		if (machineName != null) {
			content.setAttribute(IDTag.XML_common_id, machineName);
		}

		List<SourceNodeModel> nodes = getNodes();
		List<SourceTransitionModel> transitions = getTransitions();

		{
			Iterator<SourceNodeModel> iter = nodes.iterator();
			while (iter.hasNext()) {
				SourceNodeModel nodeModel = iter.next();
				content.addContent(nodeModel.getXML());
			}
		}

		{
			Iterator<SourceTransitionModel> iter = transitions.iterator();
			while (iter.hasNext()) {
				SourceTransitionModel transModel = iter.next();
				content.addContent(transModel.getXML());
			}
		}
		return content;
	}*/
}