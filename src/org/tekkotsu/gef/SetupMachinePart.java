package org.tekkotsu.gef;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.draw2d.IFigure;
import org.eclipse.gef.editparts.AbstractGraphicalEditPart;
import org.tekkotsu.api.Graphical;
import org.tekkotsu.api.NodeInstance;
import org.tekkotsu.api.SetupMachine;

public class SetupMachinePart extends AbstractGraphicalEditPart {

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
		
	}
	
	//Refresh all the visuals with the updated values.
	protected void refreshVisuals(){
		
		//Get the figure
		SetupMachineFigure fig = (SetupMachineFigure)getFigure();
		//Get the model
		SetupMachine setup = (SetupMachine)getModel();
		
		//Set the labeltext to the instance label
		fig.setLabelText("Setup Machine");
	}
	
	//Method to return children (graphical)
	public List<Graphical> getModelChildren(){
		
		//Define initialize the list to return
		ArrayList<Graphical> list = new ArrayList<Graphical>();
	
		//Get the model
		SetupMachine setup = (SetupMachine)getModel();
		
		//Get the list of nodeinstances
		ArrayList<NodeInstance> nodes = setup.getNodes();
		
		//Cast and add all the nodes from the list to the children list
		for(int i = 0; i < nodes.size(); i++){
			list.add(nodes.get(i));
		}
		
		return list;

	}
	
}
