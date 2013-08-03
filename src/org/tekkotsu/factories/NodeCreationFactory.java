package org.tekkotsu.factories;


import java.util.ArrayList;
import java.util.HashMap;

import org.eclipse.gef.requests.CreationFactory;
import org.tekkotsu.api.DefaultClassReader;
import org.tekkotsu.api.NodeClass;
import org.tekkotsu.api.NodeInstance;
import org.tekkotsu.api.SetupMachine;
import org.tekkotsu.gef.StateMachineEditor;


//Class generates object types
public class NodeCreationFactory implements CreationFactory{

	//Attributes
	private Class<?> template;
	private String name;
	
	//was Class<?> t
	public NodeCreationFactory( Class<?> v, String t) {
		
		this.template = v;
		this.name=t;
	}
	
	
	@Override
	public Object getNewObject() {
		
		if (template == NodeInstance.class) {

				NodeClass nClass = StateMachineEditor.getNodesMap().get(name);
				
				NodeInstance instance = new NodeInstance(nClass);
				
				return instance;
					
			}
				
               return null;
	}

	@Override
	public Object getObjectType() {
		return template;
	}

}
