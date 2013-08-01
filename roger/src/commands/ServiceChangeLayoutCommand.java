package commands;

import org.eclipse.draw2d.geometry.Rectangle;

import tgef.Employe;
import tgef.Service;

public class ServiceChangeLayoutCommand extends AbstractLayoutCommand {
	private Service model;
	private Rectangle layout;
	private Rectangle oldLayout;

	
	public void execute() {
	model.setLayout(layout);
	}
	
	public void setConstraint(Rectangle rect) {
	this.layout = rect;
	}
	
	
	public void setModel(Object model) {
		this.model = (Service)model;
		// Updates model pertaining to undo or redo
		this.oldLayout = ((Service)model).getLayout();
	}
	
	//Handles undo command
		public void undo() {
			this.model.setLayout(this.oldLayout);
		}


}
