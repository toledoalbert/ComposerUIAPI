package commands;

import org.eclipse.draw2d.geometry.Rectangle;

import tgef.Employe;
import org.tekkotsu.api.*;

public class NodeInstanceChangeLayoutCommand extends AbstractLayoutCommand{
	private NodeInstance model;
	private Rectangle layout;
	private Rectangle oldLayout;

	
	public void execute() {
	model.setLayout(layout);
	}
	
	public void setConstraint(Rectangle rect) {
	this.layout = rect;
	}
	
	
	public void setModel(Object model) {
		this.model = (NodeInstance)model;
		// Updates model pertaining to undo or redo
		this.oldLayout = ((NodeInstance)model).getLayout();

	}
	
	//Handles undo command
	public void undo() {
		this.model.setLayout(this.oldLayout);
	}

}


