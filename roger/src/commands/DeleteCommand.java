/*
 * Created on Sep 12, 2004
 */
package commands;

import java.util.Iterator;
import java.util.List;

import org.eclipse.gef.commands.Command;
import tgef.ui.editor.model.AbstractConnectionModel;
import org.tekkotsu.ui.util.AbstractModel;
import tgef.ui.editor.model.MultiTransitionModel;
import tgef.LayoutData;

import tgef.Node;

/**
 * @author asangpet
 */

public class DeleteCommand extends Command {
	//private LayoutData contentsModel;
	private Node node;
	private Node parentModel;
	private List<AbstractConnectionModel> sourceConnections;
	private List<AbstractConnectionModel> targetConnections;
	
	public static void delete(LayoutData ld, AbstractModel child)
	{
		child.doPreDelete(ld);
		ld.removeChild(child);
	}
	
	@Override
	public void execute() {
		//delete(contentsModel, node);
		this.parentModel.removeChild(node);
	}
	
	public void setContentsModel(Object model) {
		parentModel = (Node)model;	
	}
	
	public void setStateNodeModel(Object model) {
		node = (Node) model;
	}
	
	@Override
	public void undo() {
		//contentsModel.addChild(node);
		this.parentModel.addChild(node);
		if (node instanceof MultiTransitionModel) {
			((MultiTransitionModel)node).doPostAdd();
		} else {
			for (Iterator<AbstractConnectionModel> iter = sourceConnections.iterator(); iter.hasNext();) {
				AbstractConnectionModel model = iter.next();
				model.attachSource();
				model.attachTarget();
			}

			for (Iterator<AbstractConnectionModel> iter = targetConnections.iterator(); iter.hasNext();) {
				AbstractConnectionModel model = iter.next();
				model.attachSource();
				model.attachTarget();
			}
		
			sourceConnections.clear();
			targetConnections.clear();
		}
	}
}
