package org.tekkotsu.commands;

import org.eclipse.draw2d.geometry.Rectangle;
import org.tekkotsu.api.*;

public class SetupMachineChangeLayoutCommand extends AbstractLayoutCommand{
	
	//Attributes
	private SetupMachine model;
	private Rectangle layout;
	private Rectangle oldLayout;

	
	public void execute() {
		model.setShape(layout);
	}
	
	public void setConstraint(Rectangle rect) {
		this.layout = rect;
	}
	
	
	public void setModel(Object model) {
		
		this.model = (SetupMachine)model;
		
		// Updates model pertaining to undo or redo
		this.oldLayout = ((SetupMachine)model).getShape();

	}
	
	//Handles undo command
	public void undo() {
		this.model.setShape(this.oldLayout);
	}

}


