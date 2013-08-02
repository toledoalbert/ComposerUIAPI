package org.tekkotsu.gef;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.draw2d.IFigure;
import org.eclipse.gef.editparts.AbstractGraphicalEditPart;
import org.tekkotsu.api.Graphical;
import org.tekkotsu.api.NodeClass;
import org.tekkotsu.api.NodeInstance;
import org.tekkotsu.api.SetupMachine;

public class NodeClassPart extends AbstractGraphicalEditPart {

	@Override
	//Method to return the figure
	protected IFigure createFigure() {
		
		//initialize a new nodeinstance figure and set it to figure
		IFigure figure = new NodeClassFigure();
		
		//return the figure
		return figure;
	}
	
	@Override
	protected void createEditPolicies(){
		
	}
	
	//Refresh all the visuals with the updated values.
	protected void refreshVisuals(){
		
		//Get the figure
		NodeClassFigure fig = (NodeClassFigure)getFigure();
		//Get the model
		NodeClass instance = (NodeClass)getModel();
		
		//Set the labeltext to the instance label
		fig.setNameText(instance.getName());
		
	}

	//Method to return children (graphical)
	public List<Graphical> getModelChildren(){
		
		//Define initialize the list to return
		ArrayList<Graphical> list = new ArrayList<Graphical>();
	
		//Get the model
		NodeClass nClass = (NodeClass)getModel();
		
		//Get the list of subclasses
		ArrayList<NodeClass> subs = nClass.getSubClasses();
		
		//Cast and add all the nodes from the list to the children list
		for(int i = 0; i < subs.size(); i++){
			list.add(subs.get(i));
		}
		
		//Add the setupmachine if exists to the list of children too.
		if(nClass.getSetupMachine() != null){
			list.add(nClass.getSetupMachine());
		}
		
		return list;

	}	
	
}
