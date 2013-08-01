package org.tekkotsu.gef;

import org.eclipse.draw2d.IFigure;
import org.eclipse.gef.editparts.AbstractGraphicalEditPart;
import org.tekkotsu.api.NodeInstance;

public class NodeInstancePart extends AbstractGraphicalEditPart {

	@Override
	//Method to return the figure
	protected IFigure createFigure() {
		
		//initialize a new nodeinstance figure and set it to figure
		IFigure figure = new NodeInstanceFigure();
		
		//return the figure
		return figure;
	}
	
	@Override
	protected void createEditPolicies(){
		
	}
	
	//Refresh all the visuals with the updated values.
	protected void refreshVisuals(){
		
		//Get the figure
		NodeInstanceFigure fig = (NodeInstanceFigure)getFigure();
		//Get the model
		NodeInstance instance = (NodeInstance)getModel();
		
		//Set the labeltext to the instance label
		fig.setLabelText(instance.getLabel());
		//Set the type to the name of the nodeclass object in type field.
		fig.setTypeText(instance.getType().getName());
		
	}
	
}
