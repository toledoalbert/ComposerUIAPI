package org.tekkotsu.gef;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.geometry.Rectangle;
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
		
		//Layout
		fig.setLayout(instance.getShape());
		
	}

	//Method to return children (graphical)
	public List<Graphical> getModelChildren(){

		//Get the model
		NodeClass nClass = (NodeClass)getModel();
		
		//Get the list of subclasses
		ArrayList<NodeClass> subs = nClass.getSubClasses();
		
		//Cast and add all the nodes from the list to the children list
		for(int i = 0; i < subs.size(); i++){
			int x = 200*(i+1);
			subs.get(i).setShape(new Rectangle(x,40,150,150));
			nClass.addChild(subs.get(i));
		}
		
		//Add the setupmachine if exists to the list of children too.
		if(nClass.getSetupMachine() != null){
			nClass.getSetupMachine().setShape(new Rectangle(25,300,1000,300));
			nClass.addChild(nClass.getSetupMachine());
		}
		
		return nClass.getChildren();

	}	
	
}
