package creationFactory;


import java.util.ArrayList;
import java.util.HashMap;

import org.eclipse.gef.requests.CreationFactory;
import org.tekkotsu.api.DefaultClassReader;
import org.tekkotsu.api.NodeClass;
import org.tekkotsu.api.NodeInstance;
import org.tekkotsu.api.SetupMachine;
import tgef.*;

import tgef.Service;

//Class generates object types
public class NodeCreationFactory implements CreationFactory{

	
	
	private Class<?> template;
	private String test;
	
	//was Class<?> t
	public NodeCreationFactory( Class<?> v, String t) {
		this.template = v;
		this.test=t;
	}
	@Override
	public Object getNewObject() {
		//ArrayLists to read the default nodes and transitions from xml to.
		
		//System.out.println(test);
		/*if (template == null)
			return null;
		
			if (template == Service.class)
			{
				
				Service srv = new Service();
				srv.setEtage(42);
				srv.setName("Factory");
				return srv;

			}*/if (template==NodeInstance.class) {
						
				
				
				NodeClass nClass = MyGraphicalEditor.getHashMap().get(test);
				
				NodeInstance instance = new NodeInstance(nClass);
				instance.setName(nClass.getName());
				
				return instance;
					
			}
				
               return null;
	}

	@Override
	public Object getObjectType() {
		return template;

	}

}
