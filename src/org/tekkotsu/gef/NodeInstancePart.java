package org.tekkotsu.gef;


import java.beans.PropertyChangeEvent;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.draw2d.IFigure;
import org.eclipse.gef.EditPolicy;
import org.eclipse.gef.editparts.AbstractGraphicalEditPart;
import org.tekkotsu.api.Graphical;
import org.tekkotsu.api.NodeInstance;
import org.tekkotsu.policies.AppEditLayoutPolicy;

public class NodeInstancePart extends AppAbstractEditPart {

	@Override
	//Method to return the figure
	protected IFigure createFigure() {
		
		//initialize a new nodeinstance figure and set it to figure
		IFigure figure = new NodeInstanceFigure();
		
		//return the figure
		return figure;
	}
	
	@Override
	//Employs edit policies when node instance is moved or deleted
	protected void createEditPolicies(){

		//Install the appeditlayout policy
		installEditPolicy(EditPolicy.LAYOUT_ROLE, new AppEditLayoutPolicy());

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
		
		//Layout
		fig.setLayout(instance.getShape());
	}
	
	//Method to return children (graphical)
	public List<Graphical> getModelChildren(){
		return new ArrayList<Graphical>();
	}
	
	//Refreshes visuals if properties are changed (Node is moved)
	@Override
	public void propertyChange(PropertyChangeEvent evt) {
		
		//Refreshes view when moved
		if (evt.getPropertyName().equals(Graphical.PROPERTY_LAYOUT)) refreshVisuals();
		
	}
	
}
