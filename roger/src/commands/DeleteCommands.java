package commands;

import org.eclipse.gef.commands.Command;

import tgef.Node;
//Delete Command

public class DeleteCommands extends Command {
	
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
