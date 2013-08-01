/*
 * Created on Sep 18, 2004
 */
package commands;

import org.eclipse.gef.commands.Command;
import tgef.ui.editor.model.AbstractConnectionModel;
import tgef.ui.editor.model.SubConnectionModel;
import tgef.Node;
import tgef.LayoutData;

import org.tekkotsu.api.TransitionInstance;
import org.tekkotsu.ui.util.Debugger;

/**
 * @author asangpet
 */
public class CreateConnectionCommand extends Command {
	private Node source, target;
	private TransitionInstance connection;
	private LayoutData contentsModel;
	
	/*public void setContentsModel(Object model) {
		contentsModel = (LayoutData) model;
	}*/
	
	@Override
	public boolean canExecute() {
		if (source == null || target == null)
			return false;
		
		return !source.equals(target);
	}
	
	/**
	 * Attach source/target and connection model to content model
	 */
	@Override
	public void execute() {
		//Debugger.printDebug(Debugger.DEBUG_ALL,"Create connection command executed");
		connection = new TransitionInstance(source, target);
		
		
		connection.attachSource();
		connection.attachTarget();
		//contentsModel.addChild(connection);		
	}
	
	public void setConnection(Object model) {
		connection = (TransitionInstance) model;
	}
	
	//Had object paramaeter
	public void setSource(Node model) {
		//source = (Node) model;
		this.source=model;
		//connection.setSource(source);
	}
	
	public void setTarget(Node model) {
		//target = (Node) model;
		this.target=model;
		//connection.setTarget(target);
	}
	
	@Override
	public void undo() {
		connection.detachSource();
		connection.detachTarget();
		//contentsModel.removeChild(connection);
	}
}
