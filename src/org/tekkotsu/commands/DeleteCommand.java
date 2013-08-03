package org.tekkotsu.commands;

import org.eclipse.gef.commands.Command;
import org.tekkotsu.api.Graphical;

//Delete Command

public class DeleteCommand extends Command {
	
	//Attributes
	private Graphical model;
	private Graphical parentModel;
	
	public void execute() {
		this.parentModel.removeChild(model);
	}
	public void setModel(Object model) {
		this.model = (Graphical)model;
	}
	
	public void setParentModel(Object model) {
		parentModel = (Graphical)model;
	}
	
	public void undo() {
		this.parentModel.addChild(model);
	}

}
