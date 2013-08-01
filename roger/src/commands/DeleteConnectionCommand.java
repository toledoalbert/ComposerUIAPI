/*
 * Created on Sep 19, 2004
 */
package commands;

import org.eclipse.gef.commands.Command;
import org.tekkotsu.api.NodeInstance;
import org.tekkotsu.api.TransitionInstance;

import tgef.*;
import tgef.ui.editor.model.*;
/**
 * @author asangpet
 */
public class DeleteConnectionCommand extends Command {
	private AbstractConnectionModel connection;
	private LayoutData layout;

	@Override
	public void execute() {
		//layout.removeConnectionDescriptor(connection);
		connection.detachSource();
		connection.detachTarget();
		
		if (connection.getParent2() instanceof MultiTransitionModel) {
			MultiTransitionModel parent = (MultiTransitionModel) connection
					.getParent2();

			for (TransitionInstance sub : parent.getSubConnectionList()) {
				layout.removeConnectionDescriptor(sub);
				connection.detachSource();
				connection.detachTarget();
				layout.removeChild(sub);
				parent.removeSubConnection(sub);
			}
			// no more subconnection, remove multitransition node too
			layout.removeChild(parent);
		} else {
			connection.detachSource();
			connection.detachTarget();
			layout.removeChild(connection);
		}
	}

	public void setConnectionModel(Object model) {
		connection = (AbstractConnectionModel) model;
	}

	public void setContentsModel(Object model) {
		if (model instanceof LayoutData)
			layout = (LayoutData) model;
		else if (model instanceof MultiTransitionModel) {
			layout = (LayoutData) ((MultiTransitionModel) model)
					.getParent2();
		}
	}

	@Override
	public void undo() {
		MultiTransitionModel parent = (MultiTransitionModel) connection
				.getParent2();
		if (!layout.getPartChildren().contains(parent)) {
			layout.addChild(connection.getParent2());
			parent.doPostAdd();
		}
		layout.addChild(connection);
		connection.attachSource();
		connection.attachTarget();
	}
}
