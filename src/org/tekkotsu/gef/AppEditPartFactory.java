package org.tekkotsu.gef;

import org.eclipse.gef.EditPart;
import org.eclipse.gef.EditPartFactory;
import org.eclipse.gef.editparts.AbstractGraphicalEditPart;
import org.tekkotsu.api.NodeClass;
import org.tekkotsu.api.NodeInstance;
import org.tekkotsu.api.SetupMachine;

public class AppEditPartFactory implements EditPartFactory{

	@Override
	public EditPart createEditPart(EditPart context, Object model){
		
		//Create the part and set it to null
		AbstractGraphicalEditPart part = null;
		
		//Check the type of the model
		if(model instanceof NodeClass){
			
			//set the part to a new nodeclasspart object
			part = new NodeClassPart();
			
		}else if(model instanceof SetupMachine){
			
			//set the part to a new setupmachinepart object
			part = new SetupMachinePart();
			
		}else if(model instanceof NodeInstance){
			
			//set the part to a new nodeinstancepart object
			part = new NodeInstancePart();
		}
		
		//set the model of the part to model
		part.setModel(model);
		
		//return part.
		return part;
		
	}
	
}
