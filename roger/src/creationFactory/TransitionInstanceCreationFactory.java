package creationFactory;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;

import org.eclipse.gef.requests.CreationFactory;
import org.tekkotsu.api.DefaultClassReader;
import org.tekkotsu.api.NodeClass;
import org.tekkotsu.api.NodeInstance;
import org.tekkotsu.api.SetupMachine;
import org.tekkotsu.api.TransitionClass;
import org.tekkotsu.api.TransitionInstance;

import tgef.MyGraphicalEditor;
import tgef.Service;

public class TransitionInstanceCreationFactory implements CreationFactory {
	 
	private Class<?> template;
	private String t;
	
	public TransitionInstanceCreationFactory( Class<?> v, String t) {
		this.template = v;
		this.t=t;
	}
	
	@Override
	public Object getNewObject() {
		//ArrayLists to read the default nodes and transitions from xml to.
		
		
		
				if (template == TransitionInstance.class) {
				
					
					TransitionClass TClass = MyGraphicalEditor.getTransitionsMap().get(t);
					
					TransitionInstance instance = new TransitionInstance(TClass);
					instance.setName(TClass.getName());
					
					return instance;

						}
						
				
               return null;
	}

	@Override
	public Object getObjectType() {
		return template;

	}
}
