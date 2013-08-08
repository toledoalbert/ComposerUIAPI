package org.tekkotsu.commands;

import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gef.commands.Command;
import org.tekkotsu.api.NodeInstance;
import org.tekkotsu.api.SetupMachine;

public class MapBuilderCreateCommand extends Command{

	//Attributes
	private SetupMachine setup;
	private NodeInstance instance;

	//Constructor
	public MapBuilderCreateCommand() {

		super();
		setup = null;
		instance = null;

	}

	//
	public void setNodeInstance(Object s) {

		if (s instanceof NodeInstance)
			this.instance = (NodeInstance)s;
	}


	public void setSetupMachine(Object e) { 
		if (e instanceof SetupMachine) 
			this.setup = (SetupMachine)e; 
	} 

	public void setLayout(Rectangle r) { 
		if (instance == null)
			return;

		instance.setShape(r);

	} 

	@Override
	public boolean canExecute() {
		if (instance == null || setup == null)
			return false;
		return true;
	}
	
	@Override
	public void execute() {
		System.out.println("execute");
		setup.addNode(instance);
		
		//Real objects
		//composer.ClassView.getNodeClass().getSetupMachine().addNode(instance);
	}

	@Override
	public boolean canUndo() {
		if (setup == null || instance == null)
			return false;

		return setup.contains(instance);
	}


	@Override
	public void undo() {
		setup.removeNode(instance);
	}



}
