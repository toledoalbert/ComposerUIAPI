package commands;

import org.eclipse.gef.commands.Command;

import tgef.Node;
//Delete Command

public class DeleteCommands extends Command {
	private Node model;
	private Node parentModel;
	
	public void execute() {
	this.parentModel.removeChild(model);
	}
	public void setModel(Object model) {
	this.model = (Node)model;
	}
	
	public void setParentModel(Object model) {
	parentModel = (Node)model;
	}
	
	public void undo() {
	this.parentModel.addChild(model);
	}

	

}
