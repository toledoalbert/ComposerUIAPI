package org.tekkotsu.gef;

import org.eclipse.gef.EditPart;
import org.eclipse.gef.EditPartFactory;
import org.eclipse.gef.editparts.AbstractGraphicalEditPart;
import org.tekkotsu.api.NodeInstance;

public class AppEditPartFactory implements EditPartFactory{

	@Override
	public EditPart createEditPart(EditPart context, Object model){
		
		//Create the part and set it to null
		AbstractGraphicalEditPart part = null;
		
		//If model is a nodeinstance
		if(model instanceof NodeInstance){
			
			//set the part to a new nodeinstancepart object
			part = new NodeInstancePart();
			
		}
		
		//set the model of the part to model
		part.setModel(model);
		
		//return part.
		return part;
		
	}
	
}
