package org.tekkotsu.gef;

import java.beans.PropertyChangeEvent;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gef.EditPolicy;
import org.eclipse.gef.editparts.AbstractGraphicalEditPart;
import org.tekkotsu.api.Graphical;
import org.tekkotsu.api.NodeInstance;
import org.tekkotsu.api.SetupMachine;
import org.tekkotsu.policies.AppDeletePolicy;
import org.tekkotsu.policies.AppEditLayoutPolicy;

public class SetupMachinePart extends AppAbstractEditPart {

	@Override
	//Method to return the figure
	protected IFigure createFigure() {
		
		//initialize a new nodeinstance figure and set it to figure
		IFigure figure = new SetupMachineFigure();
		
		//return the figure
		return figure;
	}
	
	@Override
	protected void createEditPolicies(){

		//Install the appeditlayout policy
		installEditPolicy(EditPolicy.LAYOUT_ROLE, new AppEditLayoutPolicy());
		
		//Install the delete policy
		installEditPolicy(EditPolicy.COMPONENT_ROLE, new AppDeletePolicy());
	}
	
	//Refresh all the visuals with the updated values.
	protected void refreshVisuals(){
		
		//Get the figure
		SetupMachineFigure fig = (SetupMachineFigure)getFigure();
		//Get the model
		SetupMachine setup = (SetupMachine)getModel();
		
		//Set the labeltext to the instance label
		fig.setLabelText("Setup Machine");
		
		//Set color to model color
		fig.setBackgroundColor(setup.getColor());
		
		//Layout
		fig.setLayout(setup.getShape());
	}
	
	//Method to return children (graphical)
	public List<Graphical> getModelChildren(){

		//Get the model
		SetupMachine setup = (SetupMachine)getModel();
		/*
		//Get the list of nodeinstances
		ArrayList<NodeInstance> nodes = setup.getNodes();
		
		//Cast and add all the nodes from the list to the children list
		for(int i = 0; i < nodes.size(); i++){
			int x = 200*(i+1);
			nodes.get(i).setShape(new Rectangle(x,40,150,150));
			setup.addChild2(nodes.get(i));
		}
		*/
		return setup.getChildren();

	}
	
	//Refreshes visuals if properties are changed (Node is moved)
	@Override
	public void propertyChange(PropertyChangeEvent evt) {
		
		//Refreshes view when moved
		if (evt.getPropertyName().equals(Graphical.PROPERTY_LAYOUT)) refreshVisuals();
		
		//Refreshes view when deleted
		if (evt.getPropertyName().equals(Graphical.PROPERTY_REMOVE)) refreshChildren();
		
		//Refreshes view when add
		if (evt.getPropertyName().equals(Graphical.PROPERTY_ADD)) refreshChildren();
		
		if (evt.getPropertyName().equals(Graphical.PROPERTY_COL)) refreshVisuals();
		
	}
	
}
