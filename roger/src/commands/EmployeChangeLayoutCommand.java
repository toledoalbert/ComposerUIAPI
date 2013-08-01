package commands;

import org.eclipse.draw2d.geometry.Rectangle;

import tgef.Employe;

public class EmployeChangeLayoutCommand extends AbstractLayoutCommand{
	
	
	private Employe model;
	private Rectangle layout;
	private Rectangle oldLayout;

	
	public void execute() {
	model.setLayout(layout);
	}
	
	public void setConstraint(Rectangle rect) {
	this.layout = rect;
	}
	
	
	public void setModel(Object model) {
		this.model = (Employe)model;
		// Updates model pertaining to undo or redo
		this.oldLayout = ((Employe)model).getLayout();

	}
	
	//Handles undo command
	public void undo() {
		this.model.setLayout(this.oldLayout);
	}

}
