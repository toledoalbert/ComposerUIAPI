/*
 * Created on Sep 13, 2004
 */
package commands;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.eclipse.gef.commands.Command;
import tgef.ui.editor.model.AbstractConnectionModel;
import tgef.*;

/**
 * @author asangpet
 */
public class CreateCommand extends Command {
	private LayoutData contentsModel;
	private Node stateNodeModel;
	
	@Override
	public void execute() {		
		contentsModel.addChild(stateNodeModel);		
		stateNodeModel.doPostAdd();
	}
	
	public void setContentsModel(Object model) {
		contentsModel = (LayoutData) model;
	}
	
	public void setStateNodeModel(Object model) {
		stateNodeModel = (Node) model;
	}
		
	@Override
	public void undo() {
		contentsModel.removeChild(stateNodeModel);
		List<AbstractConnectionModel> sourceConnections = new ArrayList<AbstractConnectionModel>(stateNodeModel.getModelSourceConnections());
		List<AbstractConnectionModel> targetConnections = new ArrayList<AbstractConnectionModel>(stateNodeModel.getModelTargetConnections());	
		for (Iterator<AbstractConnectionModel> iter = sourceConnections.iterator(); iter.hasNext();) {
			AbstractConnectionModel model = iter.next();
			model.detachSource();
			model.detachTarget();
			contentsModel.removeChild(model);
		}

		for (Iterator<AbstractConnectionModel> iter = targetConnections.iterator(); iter.hasNext();) {
			AbstractConnectionModel model = iter.next();
			model.detachSource();
			model.detachTarget();
			contentsModel.removeChild(model);
		}		
	}
}
